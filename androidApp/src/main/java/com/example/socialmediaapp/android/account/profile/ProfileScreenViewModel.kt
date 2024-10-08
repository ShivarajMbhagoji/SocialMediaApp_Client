package com.example.socialmediaapp.android.account.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaapp.account.domain.model.Profile
import com.example.socialmediaapp.account.domain.usecases.GetProfileUseCase
import com.example.socialmediaapp.android.common.util.Constants

import com.example.socialmediaapp.android.common.util.DefaultPagingManager
import com.example.socialmediaapp.android.common.util.Event
import com.example.socialmediaapp.android.common.util.EventBus
import com.example.socialmediaapp.android.common.util.PagingManager
import com.example.socialmediaapp.common.model.Post
import com.example.socialmediaapp.follow.domain.usecase.FollowOrUnfollowUseCase
import kotlinx.coroutines.launch
import com.example.socialmediaapp.common.util.Result
import com.example.socialmediaapp.post.domain.usecase.GetUserPostsUseCase
import com.example.socialmediaapp.post.domain.usecase.LikeOrDislikePostUseCase
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect


class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val followOrUnfollowUseCase: FollowOrUnfollowUseCase,
    private val getUserPostsUseCase: GetUserPostsUseCase,
    private val likePostUseCase: LikeOrDislikePostUseCase
) : ViewModel(){

    var userInfoUiState by mutableStateOf(UserInfoUiState())
        private set

    var profilePostsUiState by mutableStateOf(ProfilePostsUiState())
        private set

    private lateinit var pagingManager: PagingManager<Post>


    private fun fetchProfile(userId: Long){
        viewModelScope.launch {
            getProfileUseCase(profileId = userId)
                .onEach {
                    when(it){
                        is Result.Error<*> -> {
                            userInfoUiState = userInfoUiState.copy(
                                isLoading = false,
                                errorMessage = it.message
                            )
                        }
                        is Result.Success -> {
                            userInfoUiState = userInfoUiState.copy(
                                isLoading = false,
                                profile = it.data
                            )
                            fetchProfilePosts(profileId = userId)
                        }
                    }
                }.collect()
        }
    }

    private suspend fun fetchProfilePosts(profileId: Long) {
        if (profilePostsUiState.isLoading || profilePostsUiState.posts.isNotEmpty()) return

        if (!::pagingManager.isInitialized){
            pagingManager = createPagingManager(profileId = profileId)
        }

        pagingManager.loadItems()
    }

    private fun createPagingManager(profileId: Long): PagingManager<Post> {
        return DefaultPagingManager(
            onRequest = { page ->
                getUserPostsUseCase(
                    userId = profileId,
                    page = page,
                    pageSize = Constants.DEFAULT_REQUEST_PAGE_SIZE
                )
            },
            onSuccess = {posts, _ ->
                profilePostsUiState = if (posts.isEmpty()) {
                    profilePostsUiState.copy(
                        endReached = true
                    )
                } else {
                    profilePostsUiState.copy(
                        posts = profilePostsUiState.posts + posts,
                        endReached = posts.size < Constants.DEFAULT_REQUEST_PAGE_SIZE
                    )
                }
            },
            onError = {message, _ ->
                profilePostsUiState = profilePostsUiState.copy(
                    errorMessage = message
                )
            },
            onLoadStateChange = {isLoading ->
                profilePostsUiState = profilePostsUiState.copy(isLoading = isLoading)
            }
        )
    }

    private fun loadMorePosts() {
        if (profilePostsUiState.endReached) return
        viewModelScope.launch { pagingManager.loadItems() }
    }

    private fun followUser(profile: Profile){
        viewModelScope.launch {
            val count = if (profile.isFollowing) -1 else +1
            userInfoUiState = userInfoUiState.copy(
                profile = userInfoUiState.profile?.copy(
                    isFollowing = !profile.isFollowing,
                    followersCount = profile.followersCount.plus(count)
                )
            )

            val result = followOrUnfollowUseCase(
                followedUserId = profile.id,
                shouldFollow = !profile.isFollowing
            )

            when(result){
                is Result.Error -> {
                    userInfoUiState = userInfoUiState.copy(
                        profile = userInfoUiState.profile?.copy(
                            isFollowing = profile.isFollowing,
                            followersCount = profile.followersCount
                        )
                    )
                }
                is Result.Success -> Unit
            }
        }
    }

    private fun likeOrDislikePost(post: Post) {
        viewModelScope.launch {
            val count = if (post.isLiked) -1 else +1
            val updatedPost = post.copy(
                isLiked = !post.isLiked,
                likesCount = post.likesCount.plus(count)
            )

            updatePost(updatedPost)

            val result = likePostUseCase(
                post = post,
            )

            when(result){
                is Result.Error -> {
                    updatePost(post)
                }
                is Result.Success -> {
                    EventBus.send(Event.PostUpdated(updatedPost))
                }
            }
        }
    }

    private fun updatePost(post: Post) {
        profilePostsUiState = profilePostsUiState.copy(
            posts = profilePostsUiState.posts.map {
                if (it.postId == post.postId) post else it
            }
        )
    }

    fun onUiAction(uiAction: ProfileUiAction) {
        when (uiAction) {
            is ProfileUiAction.FetchProfileAction -> fetchProfile(uiAction.profileId)
            is ProfileUiAction.FollowUserAction -> followUser(uiAction.profile)
            is ProfileUiAction.LoadMorePostsAction -> loadMorePosts()
            is ProfileUiAction.PostLikeAction -> likeOrDislikePost(uiAction.post)
        }
    }
}

data class UserInfoUiState(
    val isLoading: Boolean = true,
    val profile: Profile? = null,
    val errorMessage: String? = null
)

data class ProfilePostsUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = listOf(),
    val errorMessage: String? = null,
    val endReached: Boolean = false
)

sealed interface ProfileUiAction {
    data class FetchProfileAction(val profileId: Long) : ProfileUiAction
    data class FollowUserAction(val profile: Profile) : ProfileUiAction

    data object LoadMorePostsAction : ProfileUiAction

    data class PostLikeAction(val post: Post) : ProfileUiAction
}
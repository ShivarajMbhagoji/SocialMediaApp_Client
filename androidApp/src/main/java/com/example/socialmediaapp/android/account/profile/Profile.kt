package com.example.socialmediaapp.android.account.profile

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.EditProfileDestination
import com.ramcosta.composedestinations.generated.destinations.FollowersDestination
import com.ramcosta.composedestinations.generated.destinations.FollowingDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination<RootGraph>
fun Profile(
    userId: Int,
    navigator: DestinationsNavigator
) {

    val viewModel: ProfileViewModel = koinViewModel()

    ProfileScreen(
        userInfoUiState = viewModel.userInfoUiState,
        postsUiState = viewModel.profilePostsUiState,
        onButtonClick = { navigator.navigate(EditProfileDestination(userId))},
        onFollowersClick = { navigator.navigate(FollowersDestination(userId))},
        onFollowingClick = { navigator.navigate(FollowingDestination(userId))},
        onPostClick = {},
        onLikeClick = {},
        onCommentClick = {},
        fetchData = { viewModel.fetchProfile(userId)}
    )
}
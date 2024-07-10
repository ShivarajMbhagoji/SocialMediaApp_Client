package com.example.socialmediaapp.android.account.profile

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
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
        onButtonClick = { },
        onFollowersClick = { },
        onFollowingClick = { },
        onPostClick = {},
        onLikeClick = {},
        onCommentClick = {},
        fetchData = { viewModel.fetchProfile(userId)}
    )
}
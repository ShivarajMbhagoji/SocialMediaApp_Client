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
    userId: Long,
    navigator: DestinationsNavigator
) {

    val viewModel: ProfileViewModel = koinViewModel()

    ProfileScreen(
        userInfoUiState = viewModel.userInfoUiState,
        profilePostsUiState = viewModel.profilePostsUiState,
        profileId = userId,
        onUiAction = viewModel::onUiAction,
        onFollowButtonClick = {
            viewModel.userInfoUiState.profile?.let { profile ->
                if (profile.isOwnProfile) {
                    navigator.navigate(EditProfileDestination(userId))
                } else {
                    viewModel.onUiAction(ProfileUiAction.FollowUserAction(profile = profile))
                }
            }
        },
        onFollowersScreenNavigation = { navigator.navigate(FollowersDestination(userId)) },
        onFollowingScreenNavigation = { navigator.navigate(FollowingDestination(userId)) },
        onPostDetailNavigation = {}
    )
}
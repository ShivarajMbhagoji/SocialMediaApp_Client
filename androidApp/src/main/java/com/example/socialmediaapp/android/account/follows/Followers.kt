package com.example.socialmediaapp.android.account.follows

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.ProfileDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination<RootGraph>
fun Followers(
    navigator: DestinationsNavigator,
    userId: Long
) {
    val viewModel: FollowsViewModel = koinViewModel()

    FollowsScreen(
        uiState = viewModel.uiState,
        userId = userId,
        followsType = 1,
        onUiAction = viewModel::onUiAction,
        onProfileNavigation = { navigator.navigate(ProfileDestination(it)) }
    )
}
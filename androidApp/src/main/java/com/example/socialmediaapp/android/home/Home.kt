package com.example.socialmediaapp.android.home

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination<RootGraph>(start = true)
fun Home(
    navigator: DestinationsNavigator
) {
    val viewModel: HomeScreenViewModel = koinViewModel()

    HomeScreen(
        onBoardingUiState = viewModel.onBoardingUiState,
        postsUiState = viewModel.postsUiState,
        onPostClick = {},
        onProfileClick = {},
        onLikeClick = { /*TODO*/ },
        onCommentClick = { /*TODO*/ },
        onFollowButtonClick = {_,_ ->},
        onBoardingFinish = { /*TODO*/ },
        fetchData = {
            viewModel.fetchData()
        }
    )
}
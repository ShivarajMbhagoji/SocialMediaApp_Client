package com.example.socialmediaapp.android.post

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination<RootGraph>
fun PostDetail(
    navigator: DestinationsNavigator,
    postId: String
) {
    val viewModel: PostDetailViewModel = koinViewModel()

    PostDetailScreen(
        postUiState = viewModel.postUiState,
        commentsUiState = viewModel.commentsUiState,
        fetchData = {viewModel.fetchData(postId)}
    )
}
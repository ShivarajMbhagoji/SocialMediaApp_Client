package com.example.socialmediaapp.android.account.edit

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination<RootGraph>
fun EditProfile(
    navigator: DestinationsNavigator,
    userId: Long
) {
    val viewModel: EditProfileViewModel = koinViewModel()

    EditProfileScreen(
        editProfileUiState = viewModel.uiState,
        onNameChange = viewModel::onNameChange,
        bioTextFieldValue = viewModel.bioTextFieldValue,
        onBioChange = viewModel::onBioChange,
        onUploadSucceed = { navigator.navigateUp() },
        userId = userId,
        onUiAction = viewModel::onUiAction
    )
}
package com.example.socialmediaapp.android.auth.signup

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.LoginDestination
import com.ramcosta.composedestinations.generated.destinations.SignUpDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>
@Composable
fun SignUp(
    navigator: DestinationsNavigator
) {
    val viewModel: SignUpViewModel = koinViewModel()
    SignUpScreen(
        uiState = viewModel.uiState,
        onUsernameChange = viewModel::updateUsername,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onNavigateToLogin = {
            navigator.navigate(LoginDestination){
                popUpTo(SignUpDestination.invoke()){
                    inclusive=true
                }
            }
        },
        onNavigateToHome = {
                           navigator.navigate(HomeScreenDestination){
                               popUpTo(SignUpDestination.invoke()){
                                   inclusive=true
                               }
                           }
        },
        onSignUpClick = viewModel::signUp
    )
}
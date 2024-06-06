package com.example.socialmediaapp.android.auth.login

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.LoginDestination
import com.ramcosta.composedestinations.generated.destinations.SignUpDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>(start=true)
@Composable
fun Login(
    navigator: DestinationsNavigator
) {
    val viewModel: LoginViewModel = koinViewModel()
    LoginScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onSignInClick = viewModel::signIn,
        onNavigateToHome = {
            navigator.navigate(HomeScreenDestination) {
                popUpTo(LoginDestination.invoke()) {
                    inclusive = true
                }
            }
        },
        onNavigateToSignup = {
            navigator.navigate(SignUpDestination){
                popUpTo(LoginDestination.invoke()){
                    inclusive=true
                }
            }
        }
    )
}
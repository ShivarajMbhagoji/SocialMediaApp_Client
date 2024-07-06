package com.example.socialmediaapp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.socialmediaapp.android.common.AppBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.LoginDestination


@Composable
fun SocialApp(
    token:String?
) {
    val navHostController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val systemUiController = rememberSystemUiController()

    val isSystemInDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemInDark){
        MaterialTheme.colors.surface
    }else{
        MaterialTheme.colors.surface.copy(alpha = 0.95f)
    }
    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor, darkIcons = !isSystemInDark)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(modifier = Modifier, navHostController = navHostController)
        }
    ) {innerPaddings ->
        DestinationsNavHost(
            modifier = Modifier.padding(innerPaddings),
            navGraph = NavGraphs.root,
            navController = navHostController
        )
    }

    LaunchedEffect(key1 = token, block = {
        if (token != null && token.isEmpty()){
            navHostController.navigate(LoginDestination.route){
                popUpTo(HomeScreenDestination.route){
                    inclusive = true
                }
            }
        }
    })
}
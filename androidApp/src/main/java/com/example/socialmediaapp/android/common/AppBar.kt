package com.example.socialmediaapp.android.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.socialmediaapp.android.R
import com.example.socialmediaapp.android.common.theme.SmallElevation
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.LoginDestination
import com.ramcosta.composedestinations.generated.destinations.SignUpDestination
import com.ramcosta.composedestinations.utils.currentDestinationAsState

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val currentDestination = navHostController.currentDestinationAsState().value

    Surface(
        modifier = modifier,
        elevation = SmallElevation
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = getAppBarTitle(currentDestination?.route)))
            },
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.surface,
            actions = {
                AnimatedVisibility(visible = currentDestination?.route == HomeScreenDestination.route) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.person_circle_icon),
                            contentDescription = null
                        )
                    }
                }
            },
            navigationIcon = if (shouldShowNavigationIcon(currentDestination?.route)) {
                {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_back),
                            contentDescription = null
                        )
                    }
                }
            }else {
                null
            }
        )
    }
}

private fun getAppBarTitle(currentDestinationRoute: String?): Int {
    return when (currentDestinationRoute) {
        LoginDestination.route -> R.string.login_destination_title
        SignUpDestination.route -> R.string.signup_destination_title
        HomeScreenDestination.route -> R.string.home_destination_title
        else -> R.string.no_destination_title
    }
}

private fun shouldShowNavigationIcon(currentDestinationRoute: String?): Boolean {
    return false
}
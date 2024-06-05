package com.dipumba.ytsocialapp.android.common.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes


import androidx.compose.runtime.Composable

import com.example.socialmediaapp.android.common.theme.Typography
import com.example.socialmediaapp.android.common.theme.shapes


@Composable
fun SocialMediaAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colors= colors,
        shapes = shapes,
        typography = Typography,
        content = content
    )
}

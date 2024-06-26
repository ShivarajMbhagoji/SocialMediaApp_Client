package com.example.socialmediaapp.android.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Composable
@Destination<RootGraph>
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        Text(text = "HomeScreen")

    }

}
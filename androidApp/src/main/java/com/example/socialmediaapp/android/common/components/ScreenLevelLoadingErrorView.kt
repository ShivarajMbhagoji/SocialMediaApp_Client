package com.example.socialmediaapp.android.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.socialmediaapp.android.R
import com.example.socialmediaapp.android.common.theme.ExtraLargeSpacing

@Composable
internal fun ScreenLevelLoadingErrorView(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.loading_error_message),
                style = MaterialTheme.typography.caption
            )
            OutlinedButton(
                onClick = onRetry,
                shape = RoundedCornerShape(percent = 50),
                contentPadding = PaddingValues(horizontal = ExtraLargeSpacing)
            ) {
                Text(text = stringResource(id = R.string.retry_button_text))
            }
        }
    }
}
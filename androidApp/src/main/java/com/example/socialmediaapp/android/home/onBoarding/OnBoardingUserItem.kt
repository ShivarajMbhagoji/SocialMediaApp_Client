package com.example.socialmediaapp.android.home.onBoarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialmediaapp.android.MyApplicationTheme
import com.example.socialmediaapp.android.R
import com.example.socialmediaapp.android.common.components.CircleImage
import com.example.socialmediaapp.android.common.components.FollowsButton
import com.example.socialmediaapp.android.common.dummy_data.sampleUsers
import com.example.socialmediaapp.android.common.theme.MediumSpacing
import com.example.socialmediaapp.android.common.theme.SmallSpacing
import com.example.socialmediaapp.android.common.util.toCurrentUrl
import com.example.socialmediaapp.common.model.FollowsUser

@Composable
fun OnBoardingUserItem(
    modifier: Modifier = Modifier,
    followsUser: FollowsUser,
    onUserClick: (FollowsUser) -> Unit,
    isFollowing: Boolean = false,
    onFollowButtonClick: (Boolean, FollowsUser) -> Unit
) {
    Card(
        modifier = modifier
            .size(height = 140.dp, width = 130.dp)
            .clickable {
                onUserClick(followsUser)
            },
        elevation = 0.dp
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(MediumSpacing),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleImage(
                modifier = modifier.size(50.dp),
                url = followsUser.imageUrl?.toCurrentUrl(),
                onClick = {}
            )

            Spacer(modifier = modifier.height(SmallSpacing))

            Text(
                text = followsUser.name,
                style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.Medium),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = modifier.height(MediumSpacing))

            FollowsButton(
                text = if (!followsUser.isFollowing) {
                    R.string.follow_text_label
                } else R.string.unfollow_text_label,
                onClick = { onFollowButtonClick(!isFollowing, followsUser) },
                modifier = modifier
                    .heightIn(30.dp)
                    .widthIn(100.dp),
                isOutline = followsUser.isFollowing
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingUserPreview() {
    MyApplicationTheme {
        OnBoardingUserItem(
            followsUser = sampleUsers.first().toFollowsUser(),
            onUserClick = {},
            onFollowButtonClick = { _, _ -> })
    }
}
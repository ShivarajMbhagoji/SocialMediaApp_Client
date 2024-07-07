package com.example.socialmediaapp.android.home.onBoarding

import com.example.socialmediaapp.android.common.fake_data.FollowsUser

data class OnBoardingUiState(
    val isLoading: Boolean = false,
    val users: List<FollowsUser> = listOf(),
    val errorMessage: String? = null,
    val shouldShowOnBoarding: Boolean = false
)
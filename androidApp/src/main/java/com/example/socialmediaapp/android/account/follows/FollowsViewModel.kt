package com.example.socialmediaapp.android.account.follows

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaapp.android.common.dummy_data.SampleFollowsUser
import com.example.socialmediaapp.android.common.dummy_data.sampleUsers

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FollowsViewModel : ViewModel(){

    var uiState by mutableStateOf(FollowsUiState())
        private set

    fun fetchFollows(userId: Long, followsType: Int){
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            delay(1000)

            uiState = uiState.copy(isLoading = false, sampleFollowsUsers = sampleUsers)
        }
    }

}

data class FollowsUiState(
    val isLoading: Boolean = false,
    val sampleFollowsUsers: List<SampleFollowsUser> = listOf(),
    val errorMessage: String? = null
)
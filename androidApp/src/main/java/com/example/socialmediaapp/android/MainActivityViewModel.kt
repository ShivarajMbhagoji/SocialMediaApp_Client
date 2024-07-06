package com.example.socialmediaapp.android

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import com.example.socialmediaapp.android.common.datastore.UserSettings
import com.example.socialmediaapp.android.common.datastore.toAuthResultData
import kotlinx.coroutines.flow.map

class MainActivityViewModel(
    dataStore:DataStore<UserSettings>
) :ViewModel(){
    val authState = dataStore.data.map {
        it.toAuthResultData().token
    }
}
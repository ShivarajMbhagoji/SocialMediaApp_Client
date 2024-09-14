package com.example.socialmediaapp.common.data

import androidx.datastore.core.DataStore
import com.example.socialmediaapp.common.data.local.UserPreferences
import com.example.socialmediaapp.common.data.local.UserSettings
import kotlinx.coroutines.flow.first

internal class AndroidUserPreferences(
    private val dataStore: DataStore<UserSettings>
): UserPreferences{
    override suspend fun getUserData(): UserSettings {
        return dataStore.data.first()
    }

    override suspend fun setUserData(userSettings: UserSettings) {
        dataStore.updateData { userSettings }
    }
}
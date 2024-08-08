package com.example.socialmediaapp.common.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.example.socialmediaapp.common.data.local.PREFERENCES_FILE_NAME
import com.example.socialmediaapp.common.data.local.UserPreferences
import com.example.socialmediaapp.common.data.local.UserSettings


internal class IOSUserPreferences(
    private val dataStore: DataStore<Preferences>
): UserPreferences {
    override suspend fun getUserData(): UserSettings {
        TODO("Not yet implemented")
    }

    override suspend fun setUserData(userSettings: UserSettings) {
        TODO("Not yet implemented")
    }
}


internal fun createDatastore(): DataStore<Preferences>{
    return PreferenceDataStoreFactory.createWithPath(
        corruptionHandler = null,
        migrations = emptyList(),
        produceFile = {
            TODO()
        }
    )
}
package com.example.socialmediaapp.di

import com.example.socialmediaapp.common.data.IOSUserPreferences
import com.example.socialmediaapp.common.data.createDatastore
import com.example.socialmediaapp.common.data.local.UserPreferences
import org.koin.dsl.module

actual val platformModule = module {
    single<UserPreferences> { IOSUserPreferences(get()) }

    single {
        createDatastore()
    }
}
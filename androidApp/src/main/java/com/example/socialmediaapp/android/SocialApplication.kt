package com.example.socialmediaapp.android

import android.app.Application
import com.example.socialmediaapp.android.di.appModule
import com.example.socialmediaapp.di.getSharedModules
import org.koin.core.context.GlobalContext.startKoin


class SocialApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}
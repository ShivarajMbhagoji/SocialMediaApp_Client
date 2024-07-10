package com.example.socialmediaapp.android.di


import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.socialmediaapp.android.MainActivityViewModel
import com.example.socialmediaapp.android.account.edit.EditProfileViewModel
import com.example.socialmediaapp.android.account.follows.FollowsViewModel
import com.example.socialmediaapp.android.account.profile.ProfileViewModel
import com.example.socialmediaapp.android.auth.login.LoginViewModel
import com.example.socialmediaapp.android.auth.signup.SignUpViewModel
import com.example.socialmediaapp.android.common.datastore.UserSettingsSerializer
import com.example.socialmediaapp.android.home.HomeScreenViewModel
import com.example.socialmediaapp.android.post.PostDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule= module {
    viewModel { SignUpViewModel(get(),get())}
    viewModel { LoginViewModel(get(),get()) }
    viewModel { MainActivityViewModel(get()) }
    viewModel { HomeScreenViewModel() }
    viewModel { PostDetailViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { EditProfileViewModel() }
    viewModel { FollowsViewModel() }



    single {
        DataStoreFactory.create(
            serializer = UserSettingsSerializer,
            produceFile = {
                androidContext().dataStoreFile(fileName = "social-app-prefs")
            }
        )
    }
}
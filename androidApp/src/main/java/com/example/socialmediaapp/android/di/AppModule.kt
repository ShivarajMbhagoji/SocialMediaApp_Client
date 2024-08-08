package com.example.socialmediaapp.android.di


import com.example.socialmediaapp.android.MainActivityViewModel
import com.example.socialmediaapp.android.account.edit.EditProfileViewModel
import com.example.socialmediaapp.android.account.follows.FollowsViewModel
import com.example.socialmediaapp.android.account.profile.ProfileViewModel
import com.example.socialmediaapp.android.auth.login.LoginViewModel
import com.example.socialmediaapp.android.auth.signup.SignUpViewModel
import com.example.socialmediaapp.android.home.HomeScreenViewModel
import com.example.socialmediaapp.android.post.PostDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule= module {
    viewModel { SignUpViewModel(get())}
    viewModel { LoginViewModel(get()) }
    viewModel { MainActivityViewModel(get()) }
    viewModel { HomeScreenViewModel() }
    viewModel { PostDetailViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { EditProfileViewModel() }
    viewModel { FollowsViewModel() }




}
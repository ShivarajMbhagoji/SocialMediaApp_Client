package com.example.socialmediaapp.android.di


import com.example.socialmediaapp.android.auth.login.LoginViewModel
import com.example.socialmediaapp.android.auth.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule= module {
    viewModel { SignUpViewModel(get())}
    viewModel { LoginViewModel() }
}
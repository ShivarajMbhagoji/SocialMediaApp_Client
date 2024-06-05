package com.example.socialmediaapp.di


import com.example.socialmediaapp.auth.data.AuthRepositoryImpl
import com.example.socialmediaapp.auth.data.AuthService
import com.example.socialmediaapp.auth.domain.repository.AuthRepository
import com.example.socialmediaapp.auth.domain.usecases.SignInUseCase
import com.example.socialmediaapp.auth.domain.usecases.SignUpUseCase
import com.example.socialmediaapp.common.util.provideDispatcher
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

fun getSharedModules() = listOf(authModule, utilityModule)


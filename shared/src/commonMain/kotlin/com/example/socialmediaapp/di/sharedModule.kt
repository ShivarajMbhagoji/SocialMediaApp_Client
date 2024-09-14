package com.example.socialmediaapp.di


import com.example.socialmediaapp.auth.data.AuthRepositoryImpl
import com.example.socialmediaapp.auth.data.AuthService
import com.example.socialmediaapp.auth.domain.repository.AuthRepository
import com.example.socialmediaapp.auth.domain.usecases.SignInUseCase
import com.example.socialmediaapp.auth.domain.usecases.SignUpUseCase
import com.example.socialmediaapp.common.data.remote.FollowsApiService
import com.example.socialmediaapp.common.data.remote.PostApiService
import com.example.socialmediaapp.follow.data.FollowsRepositoryImpl
import com.example.socialmediaapp.follow.domain.FollowsRepository
import com.example.socialmediaapp.follow.domain.usecase.FollowOrUnfollowUseCase
import com.example.socialmediaapp.follow.domain.usecase.GetFollowableUsersUseCase
import com.example.socialmediaapp.post.data.PostRepositoryImpl
import com.example.socialmediaapp.post.domain.PostRepository
import com.example.socialmediaapp.post.domain.usecase.GetPostsUseCase
import com.example.socialmediaapp.post.domain.usecase.LikeOrUnlikePostUseCase
import com.example.socialmediaapp.provideDispatcher

import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val postModule = module {
    factory { PostApiService() }
    factory { GetPostsUseCase() }
    factory { LikeOrUnlikePostUseCase() }

    single<PostRepository> { PostRepositoryImpl(get(), get(), get()) }
}

private val followsModule = module {
    factory { FollowsApiService() }
    factory { GetFollowableUsersUseCase() }
    factory { FollowOrUnfollowUseCase() }

    single<FollowsRepository> { FollowsRepositoryImpl(get(), get(), get()) }
}

fun getSharedModules() = listOf(
    platformModule,
    authModule,
    utilityModule,
    postModule,
    followsModule
)
package com.example.socialmediaapp.di


import com.example.socialmediaapp.account.data.AccountApiService
import com.example.socialmediaapp.account.data.repository.ProfileRepositoryImpl
import com.example.socialmediaapp.account.domain.repository.ProfileRepository
import com.example.socialmediaapp.account.domain.usecases.GetProfileUseCase
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
import com.example.socialmediaapp.post.data.remote.PostCommentsApiService
import com.example.socialmediaapp.post.data.repository.PostCommentsRepositoryImpl
import com.example.socialmediaapp.post.domain.repository.PostCommentsRepository
import com.example.socialmediaapp.post.domain.repository.PostRepository
import com.example.socialmediaapp.post.domain.usecase.AddPostCommentUseCase
import com.example.socialmediaapp.post.domain.usecase.GetPostCommentsUseCase
import com.example.socialmediaapp.post.domain.usecase.GetPostUseCase
import com.example.socialmediaapp.post.domain.usecase.GetPostsUseCase
import com.example.socialmediaapp.post.domain.usecase.GetUserPostsUseCase
import com.example.socialmediaapp.post.domain.usecase.LikeOrDislikePostUseCase
import com.example.socialmediaapp.post.domain.usecase.RemovePostCommentUseCase
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
    factory { LikeOrDislikePostUseCase() }
    factory { GetUserPostsUseCase() }
    factory { GetPostUseCase() }
    single<PostRepository> { PostRepositoryImpl(get(), get(), get()) }
}

private val followsModule = module {
    factory { FollowsApiService() }
    factory { GetFollowableUsersUseCase() }
    factory { FollowOrUnfollowUseCase() }

    single<FollowsRepository> { FollowsRepositoryImpl(get(), get(), get()) }
}

private val accountModule = module {
    factory { AccountApiService() }
    factory { GetProfileUseCase() }
    single<ProfileRepository> { ProfileRepositoryImpl(get(), get(), get()) }
}

private val postCommentModule = module {
    factory { PostCommentsApiService() }
    factory { GetPostCommentsUseCase() }
    factory { AddPostCommentUseCase() }
    factory { RemovePostCommentUseCase() }

    single<PostCommentsRepository> { PostCommentsRepositoryImpl(get(), get(), get()) }
}


fun getSharedModules() = listOf(
    platformModule,
    authModule,
    utilityModule,
    postModule,
    followsModule,
    accountModule,
    postCommentModule
)
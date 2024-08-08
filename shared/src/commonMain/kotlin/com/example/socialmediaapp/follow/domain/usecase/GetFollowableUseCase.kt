package com.example.socialmediaapp.follow.domain.usecase

import com.example.socialmediaapp.common.model.FollowsUser
import com.example.socialmediaapp.common.util.Result
import com.example.socialmediaapp.follow.domain.FollowsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetFollowableUsersUseCase: KoinComponent {
    private val repository by inject<FollowsRepository>()

    suspend operator fun invoke(): Result<List<FollowsUser>> {
        return repository.getFollowableUsers()
    }
}
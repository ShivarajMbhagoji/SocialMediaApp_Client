package com.example.socialmediaapp.follow.domain.usecase

import com.example.socialmediaapp.common.model.FollowsUser
import com.example.socialmediaapp.common.util.Result
import com.example.socialmediaapp.follow.domain.FollowsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetFollowsUseCase: KoinComponent {
    private val followsRepository: FollowsRepository by inject()

    suspend operator fun invoke(
        userId: Long,
        page: Int,
        pageSize: Int,
        followsType: Int
    ): Result<List<FollowsUser>> {
        return followsRepository.getFollows(userId, page, pageSize, followsType)
    }
}
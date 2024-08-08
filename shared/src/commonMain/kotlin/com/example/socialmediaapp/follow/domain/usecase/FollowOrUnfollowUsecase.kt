package com.example.socialmediaapp.follow.domain.usecase

import com.example.socialmediaapp.common.util.Result
import com.example.socialmediaapp.follow.domain.FollowsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FollowOrUnfollowUseCase: KoinComponent {
    private val repository by inject<FollowsRepository>()

    suspend operator fun invoke(
        followedUserId: Long,
        shouldFollow: Boolean
    ): Result<Boolean> {
        return repository.followOrUnfollow(followedUserId, shouldFollow)
    }
}
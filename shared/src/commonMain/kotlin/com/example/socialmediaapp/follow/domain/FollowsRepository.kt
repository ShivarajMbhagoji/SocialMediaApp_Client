package com.example.socialmediaapp.follow.domain

import com.example.socialmediaapp.common.model.FollowsUser
import com.example.socialmediaapp.common.util.Result

interface FollowsRepository {
    suspend fun getFollowableUsers(): Result<List<FollowsUser>>
    suspend fun followOrUnfollow(followedUserId: Long, shouldFollow: Boolean): Result<Boolean>
    suspend fun getFollows(userId: Long, page: Int, pageSize: Int, followsType: Int): Result<List<FollowsUser>>
}
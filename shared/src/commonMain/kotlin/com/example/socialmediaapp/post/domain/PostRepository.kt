package com.example.socialmediaapp.post.domain

import com.example.socialmediaapp.common.model.Post
import com.example.socialmediaapp.common.util.Result

interface PostRepository {
    suspend fun getFeedPosts(page: Int, pageSize: Int): Result<List<Post>>
    suspend fun likeOrUnlikePost(postId: Long, shouldLike: Boolean): Result<Boolean>
}
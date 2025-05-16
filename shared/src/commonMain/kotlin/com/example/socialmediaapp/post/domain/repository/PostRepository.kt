package com.example.socialmediaapp.post.domain.repository

import com.example.socialmediaapp.common.model.Post
import com.example.socialmediaapp.common.util.Result

interface PostRepository {
    suspend fun getFeedPosts(page: Int, pageSize: Int): Result<List<Post>>
    suspend fun likeOrDislikePost(postId: Long, shouldLike: Boolean): Result<Boolean>

    suspend fun getUserPosts(userId: Long, page: Int, pageSize: Int): Result<List<Post>>
    suspend fun getPost(postId: Long): Result<Post>
}
package com.example.socialmediaapp.post.domain.repository

import com.example.socialmediaapp.post.domain.model.PostComment
import com.example.socialmediaapp.common.util.Result

internal interface PostCommentsRepository {
    suspend fun getPostComments(postId: Long, page: Int, pageSize: Int): Result<List<PostComment>>
    suspend fun addComment(postId: Long, content: String): Result<PostComment>
    suspend fun removeComment(postId: Long, commentId: Long): Result<PostComment?>
}
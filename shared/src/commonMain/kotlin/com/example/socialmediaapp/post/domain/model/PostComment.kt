package com.example.socialmediaapp.post.domain.model

data class PostComment(
    val commentId: Long,
    val content: String,
    val postId: Long,
    val userId: Long,
    val userName: String,
    val userImageUrl: String?,
    val createdAt: String,
    val isOwner:Boolean=false
)
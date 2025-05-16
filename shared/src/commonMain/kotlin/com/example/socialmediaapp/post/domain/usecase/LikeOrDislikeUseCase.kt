package com.example.socialmediaapp.post.domain.usecase

import com.example.socialmediaapp.common.model.Post
import com.example.socialmediaapp.common.util.Result
import com.example.socialmediaapp.post.domain.repository.PostRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LikeOrDislikePostUseCase : KoinComponent {
    private val repository by inject<PostRepository>()

    suspend operator fun invoke(post: Post): Result<Boolean> {
        return repository.likeOrDislikePost(post.postId, !post.isLiked)
    }
}
package com.example.socialmediaapp.post.domain.usecase

import com.example.socialmediaapp.common.model.Post
import com.example.socialmediaapp.common.util.Result
import com.example.socialmediaapp.post.domain.PostRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LikeOrUnlikePostUseCase : KoinComponent {
    private val repository by inject<PostRepository>()

    suspend operator fun invoke(post: Post): Result<Boolean> {
        return repository.likeOrUnlikePost(post.postId, !post.isLiked)
    }
}
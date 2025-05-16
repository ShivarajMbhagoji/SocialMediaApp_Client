package com.example.socialmediaapp.post.domain.usecase

import com.example.socialmediaapp.post.domain.model.PostComment
import com.example.socialmediaapp.post.domain.repository.PostCommentsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.example.socialmediaapp.common.util.Result

class RemovePostCommentUseCase: KoinComponent {
    private val repository: PostCommentsRepository by inject()

    suspend operator fun invoke(postId: Long, commentId: Long): Result<PostComment?> {
        return repository.removeComment(postId = postId, commentId = commentId)
    }
}
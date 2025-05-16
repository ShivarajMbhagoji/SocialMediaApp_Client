package com.example.socialmediaapp.post.domain.usecase

import com.example.socialmediaapp.post.domain.model.PostComment
import com.example.socialmediaapp.post.domain.repository.PostCommentsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.example.socialmediaapp.common.util.Result

class GetPostCommentsUseCase: KoinComponent {
    private val repository: PostCommentsRepository by inject()

    suspend operator fun invoke(
        postId: Long,
        page: Int,
        pageSize: Int
    ): Result<List<PostComment>> {
        return repository.getPostComments(postId, page, pageSize)
    }
}
package com.example.socialmediaapp.post.domain.usecase

import com.example.socialmediaapp.common.model.Post
import com.example.socialmediaapp.post.domain.repository.PostRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.example.socialmediaapp.common.util.Result

class GetPostUseCase : KoinComponent {
    private val repository by inject<PostRepository>()

    suspend operator fun invoke(postId: Long): Result<Post> {
        return repository.getPost(postId = postId)
    }
}
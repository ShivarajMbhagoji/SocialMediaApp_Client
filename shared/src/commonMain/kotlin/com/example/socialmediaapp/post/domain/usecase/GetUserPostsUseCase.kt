package com.example.socialmediaapp.post.domain.usecase

import com.example.socialmediaapp.common.model.Post
import com.example.socialmediaapp.post.domain.PostRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.example.socialmediaapp.common.util.Result

class GetUserPostsUseCase : KoinComponent {
    private val repository by inject<PostRepository>()
    suspend operator fun invoke(page: Int, pageSize: Int, userId: Long): Result<List<Post>> {
        return repository.getUserPosts(userId = userId, page = page, pageSize = pageSize)
    }
}
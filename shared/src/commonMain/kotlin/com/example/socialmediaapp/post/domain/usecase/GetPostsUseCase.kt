package com.example.socialmediaapp.post.domain.usecase

import com.example.socialmediaapp.common.model.Post
import com.example.socialmediaapp.common.util.Result
import com.example.socialmediaapp.post.domain.repository.PostRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetPostsUseCase: KoinComponent {
    private val repository by inject<PostRepository>()

    suspend operator fun invoke(
        page: Int,
        pageSize: Int
    ): Result<List<Post>> {
        return repository.getFeedPosts(page, pageSize)
    }
}
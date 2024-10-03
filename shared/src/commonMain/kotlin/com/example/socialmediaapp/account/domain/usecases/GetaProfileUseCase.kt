package com.example.socialmediaapp.account.domain.usecases

import com.example.socialmediaapp.account.domain.model.Profile
import com.example.socialmediaapp.account.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.example.socialmediaapp.common.util.Result

class GetProfileUseCase : KoinComponent {
    private val repository: ProfileRepository by inject()
    operator fun invoke(profileId: Long): Flow<Result<Profile>> {
        return repository.getProfile(profileId = profileId)
    }
}
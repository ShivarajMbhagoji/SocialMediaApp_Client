package com.example.socialmediaapp.account.domain.repository

import com.example.socialmediaapp.account.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import com.example.socialmediaapp.common.util.Result

interface ProfileRepository {
    fun getProfile(profileId: Long): Flow<Result<Profile>>
    suspend fun updateProfile(profile: Profile, imageBytes: ByteArray?): Result<Profile>
}
package com.example.socialmediaapp.account.data.repository

import com.example.socialmediaapp.account.data.AccountApiService
import com.example.socialmediaapp.account.data.model.UpdateUserParams
import com.example.socialmediaapp.account.data.model.toDomainProfile
import com.example.socialmediaapp.account.data.model.toUserSettings
import com.example.socialmediaapp.account.domain.model.Profile
import com.example.socialmediaapp.account.domain.repository.ProfileRepository
import com.example.socialmediaapp.common.data.local.UserPreferences
import com.example.socialmediaapp.common.util.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.example.socialmediaapp.common.util.Result
import com.example.socialmediaapp.common.util.safeApiCall
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json


internal class ProfileRepositoryImpl(
    private val accountApiService: AccountApiService,
    private val preferences: UserPreferences,
    private val dispatcher: DispatcherProvider
) : ProfileRepository {
    override fun getProfile(profileId: Long): Flow<Result<Profile>> {
        return flow {
            val userData = preferences.getUserData()

            // Check if the requested profile is the current user's profile
            if (profileId == userData.id) {
                emit(Result.Success(userData.toDomainProfile()))
            }

            val apiResponse = accountApiService.getProfile(
                token = userData.token,
                profileId = profileId,
                currentUserId = userData.id
            )

            when (apiResponse.code) {
                HttpStatusCode.OK -> {
                    val profile = apiResponse.data.profile!!.toProfile()

                    // Update shared preferences if the profile is the current user's profile
                    if (profileId == userData.id) {
                        preferences.setUserData(profile.toUserSettings(userData.token))
                    }
                    emit(Result.Success(profile))
                }
                else -> {
                    emit(Result.Error(message = "Error: ${apiResponse.data.message}"))
                }
            }
        }.catch { exception ->
            emit(Result.Error(message = "Error: ${exception.message}"))
        }.flowOn(dispatcher.io)
    }

    override suspend fun updateProfile(profile: Profile, imageBytes: ByteArray?): Result<Profile> {
        return safeApiCall(dispatcher) {
            val currentUserData = preferences.getUserData()

            val profileData = Json.encodeToString(
                serializer = UpdateUserParams.serializer(),
                value = UpdateUserParams(
                    userId = profile.id,
                    name = profile.name,
                    bio = profile.bio,
                    imageUrl = profile.imageUrl
                )
            )

            val apiResponse = accountApiService.updateProfile(
                token = currentUserData.token,
                profileData = profileData,
                imageBytes = imageBytes
            )

            if (apiResponse.code == HttpStatusCode.OK) {
                var imageUrl = profile.imageUrl
                if (imageBytes != null){
                    val updatedProfileApiResponse = accountApiService.getProfile(
                        token = currentUserData.token,
                        profileId = profile.id,
                        currentUserId = profile.id
                    )
                    updatedProfileApiResponse.data.profile?.let {
                        imageUrl = it.imageUrl
                    }
                }
                val updatedProfile = profile.copy(imageUrl = imageUrl)
                preferences.setUserData(updatedProfile.toUserSettings(currentUserData.token))

                Result.Success(data = updatedProfile)
            }else {
                Result.Error(message = "ApiError: ${apiResponse.data.message}")
            }
        }
    }
}
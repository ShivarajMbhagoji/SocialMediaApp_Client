package com.example.socialmediaapp.auth.data

import com.example.socialmediaapp.auth.domain.model.AuthResultData
import com.example.socialmediaapp.auth.domain.repository.AuthRepository
import com.example.socialmediaapp.common.data.local.UserPreferences
import com.example.socialmediaapp.common.data.local.toUserSettings
import com.example.socialmediaapp.common.util.DispatcherProvider
import kotlinx.coroutines.withContext
import com.example.socialmediaapp.common.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal class AuthRepositoryImpl (
    private val dispatcherProvider: DispatcherProvider,
    private val authService: AuthService,
    private val userPreferences: UserPreferences
): AuthRepository{
    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData> {
        return withContext(Dispatchers.IO) {
            try {
                val request = SignUpRequest(name, email, password)

                val authResponse = authService.signUp(request)

                if (authResponse.data == null) {
                    Result.Error(
                        message = authResponse.errorMessage!!
                    )
                } else {
                    userPreferences.setUserData(
                        authResponse.data.toAuthResultData().toUserSettings()
                    )
                    Result.Success(
                        data = authResponse.data.toAuthResultData()
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = "Oops, we could not send your request, try later!"
                )
            }
        }
    }

    override suspend fun signIn(email: String, password: String): Result<AuthResultData> {
        return withContext(Dispatchers.IO){
            try {
                val request = SignInRequest(email, password)

                val authResponse = authService.signIn(request)

                if (authResponse.data == null){
                    Result.Error(
                        message = authResponse.errorMessage!!
                    )
                }else{
                    userPreferences.setUserData(
                        authResponse.data.toAuthResultData().toUserSettings()
                    )
                    Result.Success(
                        data = authResponse.data.toAuthResultData()
                    )
                }
            }catch (e: Exception){
                Result.Error(
                    message = "Oops, we could not send your request, try later!"
                )
            }
        }
    }
}
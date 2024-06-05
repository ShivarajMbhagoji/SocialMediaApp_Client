package com.example.socialmediaapp.auth.domain.repository

import com.example.socialmediaapp.auth.domain.model.AuthResultData
import com.example.socialmediaapp.common.util.Result

interface AuthRepository {

    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData>

    suspend fun signIn(
        email: String,
        password: String
    ): Result<AuthResultData>
}
package com.example.socialmediaapp.common.data.local

import com.example.socialmediaapp.auth.domain.model.AuthResultData
import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val id: Long = 0,
    val name: String = "",
    val bio: String = "",
    val avatar: String? = null,
    val token: String = "",
    val followersCount: Int = 0,
    val followingCount: Int = 0
)


fun UserSettings.toAuthResultData():AuthResultData{
    return AuthResultData(id,name,bio,avatar,token,followersCount,followingCount)
}

fun AuthResultData.toUserSettings(): UserSettings {
    return UserSettings(
        id, name, bio, avatar, token, followersCount, followingCount
    )
}

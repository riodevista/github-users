package com.goodapps.github_users.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserItemDto(
    @SerialName("id")
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("login")
    val login: String
)
package com.goodapps.github_users.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailedUserItemDto(
    @SerialName("id")
    val id: Long,
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("login")
    val login: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("followers")
    val followers: Int,
    @SerialName("following")
    val following: Int,
    @SerialName("created_at")
    val createdAt: String? = null,
)
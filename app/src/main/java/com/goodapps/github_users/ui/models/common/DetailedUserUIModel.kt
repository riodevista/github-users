package com.goodapps.github_users.ui.models.common

import com.goodapps.github_users.domain.models.User

data class DetailedUserUIModel(
    val id: Long,
    val avatarUrl: String?,
    val login: String,
    val name: String? = null,
    val email: String? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val createdAt: String? = null
)

fun User.toUIModelDetailed() =
    DetailedUserUIModel(
        id = this.id,
        avatarUrl = this.avatarUrl,
        login = this.login,
        name = this.name,
        email = this.email,
        followers = this.followers,
        following = this.following,
        createdAt = this.createdAt
    )

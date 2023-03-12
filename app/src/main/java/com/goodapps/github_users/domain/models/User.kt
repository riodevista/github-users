package com.goodapps.github_users.domain.models

data class User(
    val id: Int,
    val avatarUrl: String? = null,
    val login: String,
    val name: String? = null,
    val email: String? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val createdAt: String? = null
)
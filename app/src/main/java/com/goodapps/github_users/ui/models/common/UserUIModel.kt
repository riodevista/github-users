package com.goodapps.github_users.ui.models.common

import com.goodapps.github_users.domain.models.User

data class UserUIModel(
    val id: Int,
    val avatarUrl: String?,
    val login: String
)

fun User.toUIModel() =
    UserUIModel(
        id = this.id,
        avatarUrl = this.avatarUrl,
        login = this.login
    )

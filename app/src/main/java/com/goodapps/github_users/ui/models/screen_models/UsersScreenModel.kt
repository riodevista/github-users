package com.goodapps.github_users.ui.models.screen_models

import com.goodapps.github_users.ui.models.Event
import com.goodapps.github_users.ui.models.common.UserUIModel

data class UsersScreenModel(
    val users: List<UserUIModel> = emptyList(),
    val showProgress: Boolean = false,
    val errorEvent: Event<String>? = null
)

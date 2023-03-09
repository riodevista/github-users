package com.goodapps.github_users.ui.models.screen_models

import com.goodapps.github_users.ui.models.Event
import com.goodapps.github_users.ui.models.common.DetailedUserUIModel

data class UsersDetailsScreenModel(
    val user: DetailedUserUIModel? = null,
    val showProgress: Boolean = false,
    val errorEvent: Event<String>? = null
)

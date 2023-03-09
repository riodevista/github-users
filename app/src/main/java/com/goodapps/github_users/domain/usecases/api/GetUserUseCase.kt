package com.goodapps.github_users.domain.usecases.api

import com.goodapps.github_users.domain.models.User

interface GetUserUseCase {
    suspend fun execute(login: String): Result<User>
}

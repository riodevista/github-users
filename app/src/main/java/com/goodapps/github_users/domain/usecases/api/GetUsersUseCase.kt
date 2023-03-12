package com.goodapps.github_users.domain.usecases.api

import com.goodapps.github_users.domain.models.User

interface GetUsersUseCase {
    suspend fun execute(since: Int?, perPage: Int?): Result<List<User>>
}

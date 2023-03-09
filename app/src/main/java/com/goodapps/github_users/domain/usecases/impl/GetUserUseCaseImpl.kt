package com.goodapps.github_users.domain.usecases.impl

import com.goodapps.github_users.data.mappers.toDomain
import com.goodapps.github_users.data.repository.users.UsersRepository
import com.goodapps.github_users.domain.models.User
import com.goodapps.github_users.domain.usecases.api.GetUserUseCase
import javax.inject.Inject


class GetUserUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : GetUserUseCase {

    override suspend fun execute(login: String): Result<User> {
        return try {
            val user = usersRepository.getUser(login)
            Result.success(user.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
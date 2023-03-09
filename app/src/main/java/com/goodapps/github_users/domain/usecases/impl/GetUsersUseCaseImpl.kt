package com.goodapps.github_users.domain.usecases.impl

import com.goodapps.github_users.data.mappers.toDomain
import com.goodapps.github_users.data.models.UserItemDto
import com.goodapps.github_users.data.repository.users.UsersRepository
import com.goodapps.github_users.domain.models.User
import com.goodapps.github_users.domain.usecases.api.GetUsersUseCase
import javax.inject.Inject


class GetUsersUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : GetUsersUseCase {

    override suspend fun execute(): Result<List<User>> {
        return try {
            val users = usersRepository.getUsers()
            Result.success(users.map(UserItemDto::toDomain))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
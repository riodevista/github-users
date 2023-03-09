package com.goodapps.github_users.data.repository.users

import com.goodapps.github_users.data.models.DetailedUserItemDto
import com.goodapps.github_users.data.models.UserItemDto


interface UsersRepository {

    suspend fun getUsers(): List<UserItemDto>

    suspend fun getUser(login: String): DetailedUserItemDto
}
package com.goodapps.github_users.data.repository.users

import com.goodapps.github_users.data.models.DetailedUserItemDto
import com.goodapps.github_users.data.models.UserItemDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : UsersRepository {

    override suspend fun getUsers() = withContext(Dispatchers.IO) {
        httpClient.get("users").body<List<UserItemDto>>()
    }

    override suspend fun getUser(login: String) = withContext(Dispatchers.IO) {
        httpClient.get("users/$login").body<DetailedUserItemDto>()
    }

}
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

    override suspend fun getUsers(since: Int?, perPage: Int?) = withContext(Dispatchers.IO) {
        httpClient.get("users") {
            if (since != null) {
                parameter("since", since.toString())
            }
            if (perPage != null) {
                parameter("per_page", perPage.toString())
            }
        }.body<List<UserItemDto>>()
    }

    override suspend fun getUser(login: String) = withContext(Dispatchers.IO) {
        httpClient.get("users/$login").body<DetailedUserItemDto>()
    }

}
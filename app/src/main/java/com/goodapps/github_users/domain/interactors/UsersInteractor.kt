package com.goodapps.github_users.domain.interactors

import com.goodapps.github_users.domain.models.User
import com.goodapps.github_users.domain.usecases.api.GetUserUseCase
import com.goodapps.github_users.domain.usecases.api.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersInteractor @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserUseCase: GetUserUseCase

) {

    private val _usersListFlow = MutableStateFlow<List<User>>(emptyList())
    val usersListFlow: StateFlow<List<User>>
        get() = _usersListFlow

    private var token: String? = null

    suspend fun fetchUsers(): Result<List<User>> = withContext(Dispatchers.IO) {
//        if (token.isNullOrBlank()) {
//            return@withContext Result.failure(IllegalAccessException("Missed GitHub User token. Please try to sign in again"))
//        }
        val result = getUsersUseCase.execute()
        if (result.isSuccess) {
            _usersListFlow.tryEmit(result.getOrDefault(emptyList()))
        }
        result
    }

    suspend fun getUserOffline(userId: Long) = withContext(Dispatchers.IO) {
        _usersListFlow.value.firstOrNull { it.id == userId }
    }

    suspend fun saveUserToken(token: String) = withContext(Dispatchers.IO) {
        this@UsersInteractor.token = token
    }

    suspend fun fetchDetailedUser(userLogin: String) = withContext(Dispatchers.IO) {
        val result = getUserUseCase.execute(userLogin)
        result
    }
}
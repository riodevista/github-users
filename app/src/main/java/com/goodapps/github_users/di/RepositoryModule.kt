package com.goodapps.github_users.di

import com.goodapps.github_users.data.repository.users.UsersRepository
import com.goodapps.github_users.data.repository.users.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUsersRepository(httpClient: HttpClient): UsersRepository {
        return UsersRepositoryImpl(httpClient)
    }
}

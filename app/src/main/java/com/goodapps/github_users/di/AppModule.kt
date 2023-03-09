package com.goodapps.github_users.di

import com.goodapps.github_users.data.repository.users.UsersRepository
import com.goodapps.github_users.domain.interactors.UsersInteractor
import com.goodapps.github_users.domain.usecases.api.GetUserUseCase
import com.goodapps.github_users.domain.usecases.api.GetUsersUseCase
import com.goodapps.github_users.domain.usecases.impl.GetUserUseCaseImpl
import com.goodapps.github_users.domain.usecases.impl.GetUsersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetUsersUseCase(repository: UsersRepository): GetUsersUseCase {
        return GetUsersUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetUserUseCase(repository: UsersRepository): GetUserUseCase {
        return GetUserUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideUsersInteractor(
        getUsersUseCase: GetUsersUseCase,
        getUserUseCase: GetUserUseCase
    ): UsersInteractor {
        return UsersInteractor(getUsersUseCase, getUserUseCase)
    }

}

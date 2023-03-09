package com.goodapps.github_users.di

import com.goodapps.github_users.core.resources.AndroidResourcesProvider
import com.goodapps.github_users.core.resources.ResourcesProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourcesModule {

    @Singleton
    @Binds
    abstract fun bindResourcesProvider(resourcesProvider: AndroidResourcesProvider): ResourcesProvider

}

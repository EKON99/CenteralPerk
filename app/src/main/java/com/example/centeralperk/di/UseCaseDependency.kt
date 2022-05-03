package com.example.centeralperk.di

import com.example.centeralperk.domain.repository.NetworkRepo
import com.example.centeralperk.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseDependency {

    @Provides
    @Singleton
    fun providesLoginUseCase(networkRepo: NetworkRepo): LoginUseCase {
        return LoginUseCase(networkRepo)
    }
}
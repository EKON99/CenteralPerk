package com.example.centeralperk.di

import com.example.centeralperk.domain.repository.NetworkRepo
import com.example.centeralperk.domain.usecase.HomeUseCase
import com.example.centeralperk.domain.usecase.LoginUseCase
import com.example.centeralperk.domain.usecase.SignUpUseCase
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

    @Provides
    @Singleton
    fun providesSignUpUseCase(networkRepo: NetworkRepo): SignUpUseCase {
        return SignUpUseCase(networkRepo)
    }

    @Provides
    @Singleton
    fun providesHomeUseCase(networkRepo: NetworkRepo): HomeUseCase {
        return HomeUseCase(networkRepo)
    }
}
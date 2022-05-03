package com.example.centeralperk.di

import com.example.centeralperk.data.network_call.ApiInterface
import com.example.centeralperk.data.repository.EventListenerImpl
import com.example.centeralperk.data.repository.NetworkRepoImpl
import com.example.centeralperk.domain.repository.EventListener
import com.example.centeralperk.domain.repository.NetworkRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryDependency {

    @Provides
    @Singleton
    fun provideEventListener(): EventListener {
        return EventListenerImpl()
    }

    @Provides
    @Singleton
    fun provideNetworkRepoImpDependency(apiInterface: ApiInterface): NetworkRepo {
        return NetworkRepoImpl(apiInterface)
    }
}
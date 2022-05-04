package com.example.centeralperk.di

import android.content.Context
import com.example.centeralperk.util.PreferenceDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependencyInjection {

    @Provides
    @Singleton
    fun providesPreferenceDataStore(context: Context): PreferenceDataStore {
        return PreferenceDataStore(context)
    }
}
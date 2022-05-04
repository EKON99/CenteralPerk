package com.example.centeralperk.di

import android.content.Context
import com.example.centeralperk.util.PreferenceDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependencyInjection {

    @Provides
    @Singleton
    fun providesPreferenceDataStore(@ApplicationContext app: Context): PreferenceDataStore {
        return PreferenceDataStore(app)
    }
}
package com.example.centeralperk.di

import android.content.Context
import com.example.centeralperk.app.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationClassDependency {

    @Provides
    @Singleton
    fun provideApplicationClass(@ApplicationContext app: Context): App {
        return app as App
    }
}
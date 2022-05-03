package com.example.centeralperk.di

import com.example.centeralperk.data.network_call.ApiInterface
import com.example.centeralperk.util.AppConstant
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkCallDependency {

    @Provides
    @Singleton
    fun provideRetrofitDependency(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiInterface::class.java)
    }
}
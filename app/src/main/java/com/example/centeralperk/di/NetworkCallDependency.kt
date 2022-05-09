package com.example.centeralperk.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.centeralperk.data.network_call.ApiInterface
import com.example.centeralperk.util.AppConstant
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkCallDependency {

    @Provides
    @Singleton
    fun provideRetrofitDependency(@ApplicationContext context: Context): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        ChuckerInterceptor.Builder(context)
                            .collector(ChuckerCollector(context) )
                            .maxContentLength(250000L)
                            .redactHeaders(emptySet())
                            .alwaysReadResponseBody(false)
                            .build()
                    )
                    .build()
            )
            .build()
            .create(ApiInterface::class.java)
    }
}
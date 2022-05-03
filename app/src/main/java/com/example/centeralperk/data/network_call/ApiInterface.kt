package com.example.centeralperk.data.network_call

import com.example.centeralperk.domain.model.Temp
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("login")
    suspend fun login(@Body json: JsonObject): Response<Temp>
}
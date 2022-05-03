package com.example.centeralperk.domain.repository

import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.domain.model.Temp
import com.google.gson.JsonObject
import retrofit2.Response

interface NetworkRepo {

    suspend fun login(jsonObject: JsonObject): ApiResponse<Temp?>
}
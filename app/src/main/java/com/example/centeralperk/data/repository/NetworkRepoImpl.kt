package com.example.centeralperk.data.repository

import com.example.centeralperk.data.network_call.ApiInterface
import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.data.source.SafeApiRequest
import com.example.centeralperk.domain.model.Temp
import com.example.centeralperk.domain.repository.NetworkRepo
import com.google.gson.JsonObject
import retrofit2.Response

class NetworkRepoImpl(
    private val apiInterface: ApiInterface
) : NetworkRepo, SafeApiRequest() {

    override suspend fun login(jsonObject: JsonObject): ApiResponse<Temp?> {
        return apiRequest { apiInterface.login(jsonObject) }
    }

}
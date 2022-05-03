package com.example.centeralperk.data.repository

import com.example.centeralperk.data.network_call.ApiInterface
import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.data.source.SafeApiRequest
import com.example.centeralperk.domain.model.LoginResponseModel
import com.example.centeralperk.domain.repository.NetworkRepo
import com.google.gson.JsonObject

class NetworkRepoImpl(
    private val apiInterface: ApiInterface
) : NetworkRepo, SafeApiRequest() {

    /**
     * Calling ApiInterface Login function
     *  @param jsonObject
     *  @return ApiResponse LoginResponseModel
     */
    override suspend fun login(jsonObject: JsonObject): ApiResponse<LoginResponseModel?> {
        return apiRequest { apiInterface.login(jsonObject) }
    }

}
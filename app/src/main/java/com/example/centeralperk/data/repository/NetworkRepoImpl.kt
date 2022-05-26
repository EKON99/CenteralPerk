package com.example.centeralperk.data.repository

import com.example.centeralperk.data.network_call.ApiInterface
import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.data.source.SafeApiRequest
import com.example.centeralperk.domain.model.LoginResponseModel
import com.example.centeralperk.domain.model.SignUpResponseModel
import com.example.centeralperk.domain.model.UserFeedResponseModel
import com.example.centeralperk.domain.model.UserProfileResponseModel
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

    /**
     * Calling ApiInterface signUp function
     *  @param jsonObject
     *  @return ApiResponse SignUpResponseModel
     */
    override suspend fun signUp(jsonObject: JsonObject): ApiResponse<SignUpResponseModel?> {
        return apiRequest { apiInterface.signUp(jsonObject) }
    }

    /**
     * Calling ApiInterface userFeed function
     *  @param page
     *  @param authToken
     *  @return ApiResponse UserFeedResponseModel
     */
    override suspend fun userFeed(
        page: String,
        authToken: String
    ): ApiResponse<UserFeedResponseModel?> {
        return apiRequest { apiInterface.userFeed(page, authToken) }
    }

    /**
     * Calling ApiInterface userProfile function
     *  @param authToken
     *  @return ApiResponse UserProfileResponseModel
     */
    override suspend fun userProfile(authToken: String): ApiResponse<UserProfileResponseModel?> {
        return apiRequest { apiInterface.userProfile(authToken) }
    }

}
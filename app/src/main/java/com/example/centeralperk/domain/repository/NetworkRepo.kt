package com.example.centeralperk.domain.repository

import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.domain.model.LoginResponseModel
import com.example.centeralperk.domain.model.SignUpResponseModel
import com.google.gson.JsonObject

interface NetworkRepo {

    /**
     * Calling ApiInterface Login function
     *  @param jsonObject
     *  @return ApiResponse LoginResponseModel
     */
    suspend fun login(jsonObject: JsonObject): ApiResponse<LoginResponseModel?>

    /**
     * Calling ApiInterface signUp function
     *  @param jsonObject
     *  @return ApiResponse SignUpResponseModel
     */
    suspend fun signUp(jsonObject: JsonObject): ApiResponse<SignUpResponseModel?>

    /**
     * Calling ApiInterface userFeed function
     *  @param page
     *  @param authToken
     *  @return ApiResponse UserFeedResponseModel
     */
    suspend fun userFeed(page: String,authToken : String): ApiResponse<UserFeedResponseModel?>
}
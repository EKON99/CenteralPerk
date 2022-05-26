package com.example.centeralperk.data.network_call

import com.example.centeralperk.domain.model.LoginResponseModel
import com.example.centeralperk.domain.model.SignUpResponseModel
import com.example.centeralperk.domain.model.UserFeedResponseModel
import com.example.centeralperk.domain.model.UserProfileResponseModel
import com.example.centeralperk.util.AppConstant
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    /**
     * Login Api request
     * @param json
     * @return Response LoginResponseModel
     */
    @POST(AppConstant.LOGIN)
    suspend fun login(@Body json: JsonObject): Response<LoginResponseModel>

    /**
     * SignUp Api request
     * @param json
     * @return Response SignUpResponseModel
     */
    @POST(AppConstant.SIGN_UP)
    suspend fun signUp(@Body json: JsonObject): Response<SignUpResponseModel>

    /**
     * User-feed Api request
     * @param p
     * @param token
     * @return Response UserFeedResponseModel
     */
    @GET(AppConstant.USER_FEED)
    suspend fun userFeed(
        @Query(AppConstant.PAGE) p: String,
        @Header(AppConstant.AUTHORIZATION) token: String
    ): Response<UserFeedResponseModel>

    /**
     * User-profile Api request
     * @param token
     * @return Response UserProfileResponseModel
     */
    @GET(AppConstant.USER_PROFILE)
    suspend fun userProfile(
        @Header(AppConstant.AUTHORIZATION) token: String
    ): Response<UserProfileResponseModel>
}
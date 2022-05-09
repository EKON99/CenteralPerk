package com.example.centeralperk.data.network_call

import com.example.centeralperk.domain.model.LoginResponseModel
import com.example.centeralperk.domain.model.SignUpResponseModel
import com.example.centeralperk.domain.model.UserFeedResponseModel
import com.example.centeralperk.util.AppConstant
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    /**
     * Login Api request
     * @param json
     * @return Response LoginResponseModel
     */
    @POST("login/")
    suspend fun login(@Body json: JsonObject): Response<LoginResponseModel>

    /**
     * SignUp Api request
     * @param json
     * @return Response SignUpResponseModel
     */
    @POST("signup/")
    suspend fun signUp(@Body json: JsonObject): Response<SignUpResponseModel>

    /**
     * User-feed Api request
     * @param p
     * @param token
     * @return Response UserFeedResponseModel
     */
    @GET("user-feed/")
    suspend fun userFeed(
        @Query(AppConstant.PAGE) p: String,
        @Body token: String
    ): Response<UserFeedResponseModel>
}
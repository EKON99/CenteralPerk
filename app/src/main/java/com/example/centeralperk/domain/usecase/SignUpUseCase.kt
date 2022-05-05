package com.example.centeralperk.domain.usecase

import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.domain.model.SignUpResponseModel
import com.example.centeralperk.domain.repository.NetworkRepo
import com.google.gson.JsonObject

class SignUpUseCase(
    private val networkRepo: NetworkRepo
) {

    /**
     * Calling network repository signUp function
     * @param jsonObject
     * @return ApiResponse LoginResponseModel
     * */
    suspend fun signUpUseCase(jsonObject: JsonObject): ApiResponse<SignUpResponseModel?> {
        return networkRepo.signUp(jsonObject)
    }
}
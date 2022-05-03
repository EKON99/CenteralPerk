package com.example.centeralperk.domain.usecase

import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.domain.model.LoginResponseModel
import com.example.centeralperk.domain.repository.NetworkRepo
import com.google.gson.JsonObject

class LoginUseCase(
    private val networkRepo: NetworkRepo
) {

    suspend fun loginUseCase(jsonObject: JsonObject) : ApiResponse<LoginResponseModel?>{
        return networkRepo.login(jsonObject)
    }

}
package com.example.centeralperk.domain.usecase

import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.domain.model.UserFeedResponseModel
import com.example.centeralperk.domain.model.UserProfileResponseModel
import com.example.centeralperk.domain.repository.NetworkRepo

class HomeUseCase(
    private val networkRepo: NetworkRepo
) {

    /**
     * Calling network repository userFeed function
     * @param page
     * @param authToken
     * @return ApiResponse UserFeedResponseModel
     * */
    suspend fun homeUserFeed(page: String, authToken: String): ApiResponse<UserFeedResponseModel?> {
        return networkRepo.userFeed(page, authToken)
    }

    /**
     * Calling network repository userProfile function
     * @param authToken
     * @return ApiResponse UserProfileResponseModel
     * */
    suspend fun homeUserProfile(authToken: String): ApiResponse<UserProfileResponseModel?> {
        return networkRepo.userProfile(authToken)
    }
}
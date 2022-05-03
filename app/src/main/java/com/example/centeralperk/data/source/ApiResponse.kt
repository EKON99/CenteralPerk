package com.example.centeralperk.data.source

sealed class ApiResponse<out T>{

    /**
     * When api hit is successful
     */
    data class SuccessFul<out S>(val successFul: S) : ApiResponse<S>()

    /**
     * When get error during api request
     */
    data class ApiError<out A>(val apiError: A) : ApiResponse<Nothing>()

    /**
     * When getting error during api hit or someThing else
     * Example - while hitting api internet connection is lost
     */
    data class UnKnownError<out U>(val unKnownError: U) : ApiResponse<Nothing>()
}
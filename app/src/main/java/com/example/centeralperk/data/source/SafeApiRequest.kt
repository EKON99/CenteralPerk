package com.example.centeralperk.data.source

import com.example.centeralperk.util.AppConstant
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

    suspend fun <T> apiRequest(call: suspend () -> Response<T>): ApiResponse<T?> {

        return try {

            /**
             * Calling the Api request
             * Storing the response in apiResponse variable
             * */
            val apiResponse = call.invoke()

            if (apiResponse.isSuccessful) {

                /**
                 *  Returning the the response body if api request is successful
                 **/

                ApiResponse.SuccessFul(apiResponse.body())

            } else {
                try {

                    /** Getting the errorBody message */
                    val error = apiResponse.errorBody().toString()
                    ApiResponse.ApiError(JSONObject(error).getString(AppConstant.MESSAGE))

                } catch (e: Exception) {

                    ApiResponse.ApiError("${AppConstant.SOMETHING_WENT_WRONG} \nException : $e")
                }
            }
        }

        /**
         * Catching IOException
         */
        catch (e: IOException) {

            ApiResponse.UnKnownError(AppConstant.SERVER_TIME_OUT)
        }

        /**
         * Catching Exception
         **/
        catch (e: Exception) {

            ApiResponse.UnKnownError("${AppConstant.SOMETHING_WENT_WRONG} \nException : $e")
        }
    }
}
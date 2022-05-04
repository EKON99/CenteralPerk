package com.example.centeralperk.app

import android.app.Application
import com.example.centeralperk.util.AppConstant
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    private var authenticationToken = ""

    /**
     * Setting authToken in authToken variable
     * @param authToken
     **/
    fun setAuthToken(authToken: String) {
        authenticationToken = authToken
    }

    /**
     * Getting authToken
     * @return authToken
     */
    fun getAuthToken(): String {
        return "${AppConstant.TOKEN} $authenticationToken"
    }
}
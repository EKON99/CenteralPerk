package com.example.centeralperk.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkChecker {

    /**
     * Checking network state
     * @param context
     * @return Boolean
     */
    fun networkCheck(context: Context): Boolean {

        /** Network connectivityManager */
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(networkInfo) ?: return false

        return when {

            /** When connected to wifi */
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            /** when connected to device cellular internet */
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            /** when connected to ethernet */
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

            /** when connected to internet via bluetooth */
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true

            /** when no internet connection */
            else -> false
        }
    }
}
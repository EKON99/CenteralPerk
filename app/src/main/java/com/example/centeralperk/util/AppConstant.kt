package com.example.centeralperk.util

class AppConstant {

    companion object {

        /** API base url */
        const val BASE_URl = "http://13.233.80.8:8000/api/"
        const val BASE_IMAGE_URL = "http://13.233.80.8:8000/media/"

        const val LOADER_TAG = "DialogBox loader"
        const val MESSAGE = "message"
        const val SOMETHING_WENT_WRONG = "Something went wrong"
        const val SERVER_TIME_OUT = "Server time out"
        const val USER_NAME_OR_EMAIL_EMPTY = "Username or email is required"
        const val PASSWORD_EMPTY = "Password is required"
        const val USER_NAME_EMPTY = "Username must be Minimum 4 characters"
        const val NAME_EMPTY = "Name is required"
        const val EMAIL_EMPTY = "Email is required"
        const val EMAIL_VALIDATION = "Enter valid email"
        const val PASSWORD_REQUIRED = "Password must be minimum 4 characters"
        const val NETWORK_CONNECTION = "Please check your internet connection"
        const val NO_DATA = "No data"
        const val REFRESH = "Refresh"
        const val ADD_FEED = "Add feed"

        /** Preference dataStore key and name */
        const val PREFERENCE_DATASTORE = "Preference dataStore"
        const val AUTH_TOKEN = "Auth Token"

        /** Api Tag */
        const val EMAIL = "email"
        const val NAME = "name"
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val TOKEN = "token"

        /** Api Targets tag */
        const val LOGIN = "login/"
        const val SIGN_UP = "signup/"
        const val USER_FEED = "user-feed/"
        const val USER_PROFILE = "user-profile/"

        /** Api request */
        const val PAGE = "p"
        const val AUTHORIZATION = "Authorization"
    }
}
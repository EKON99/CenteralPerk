package com.example.centeralperk.presentation.fragment.login

import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.centeralperk.app.App
import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.domain.repository.EventListener
import com.example.centeralperk.domain.usecase.LoginUseCase
import com.example.centeralperk.util.AppConstant
import com.example.centeralperk.util.PreferenceDataStore
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val eventListener: EventListener,
    private val app: App,
    private val preferenceDataStore: PreferenceDataStore
) : ViewModel() {

    val emailOrUserName = ObservableField("")

    val password = ObservableField("")

    /** Navigating mutable state*/
    private val navigationMutableState = MutableStateFlow(false)
    val navigate = navigationMutableState.asStateFlow()

    /** Password visibility StateFlow */
    private val visibilityMutableState = MutableStateFlow(false)
    val visibility = visibilityMutableState.asStateFlow()

    /**
     * Changing the passwordVisibility state
     **/
    fun passwordVisibility() {
        visibilityMutableState.value = visibilityMutableState.value.not()
    }

    /**
     * Calling the useCase login function
     */
    fun login() {

        /** Calling Api in coroutine  */
        viewModelScope.launch(Dispatchers.IO) {

            /** Showing loader */
            eventListener.showLoader()

            /** Creating json object for api request */
            val json = JsonObject()
            json.addProperty(AppConstant.USERNAME, emailOrUserName.get()?.trim())
            json.addProperty(AppConstant.PASSWORD, password.get())

            /** Calling the loginUseCase */
            val response = loginUseCase.loginUseCase(json)

            /** 2 seconds delay 'just to see loader ' */
            delay(2000)

            /** Hiding the loader */
            eventListener.hideLoader()

            when (response) {
                is ApiResponse.SuccessFul -> {

                    /** Storing the authToken in preference dataStore */
                    preferenceDataStore.write(
                        AppConstant.AUTH_TOKEN,
                        response.successFul?.data?.token
                    )

                    /** Storing the authToken in application class */
                    response.successFul?.data?.token?.let { token -> app.setAuthToken(token) }

                    /** Showing toast message */
                    viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            app.baseContext,
                            response.successFul?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    /** Changing navigation state*/
                    navigationMutableState.value = true

                }
                is ApiResponse.ApiError<*> -> {

                    /** Showing toast message */
                    viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            app.baseContext,
                            response.apiError.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is ApiResponse.UnKnownError<*> -> {

                    /** Showing toast message */
                    viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            app.baseContext,
                            response.unKnownError.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
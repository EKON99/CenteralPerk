package com.example.centeralperk.presentation.fragment.sign_up

import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.centeralperk.app.App
import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.domain.repository.EventListener
import com.example.centeralperk.domain.usecase.SignUpUseCase
import com.example.centeralperk.util.AppConstant
import com.example.centeralperk.util.NetworkChecker
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val eventListener: EventListener,
    private val app: App
) : ViewModel() {

    val name = ObservableField("")

    val userName = ObservableField("")

    val email = ObservableField("")

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
     * Calling signUpUseCase signUp function
     */
    fun signUp() {

        /** Calling Api in coroutine  */
        viewModelScope.launch(Dispatchers.IO) {

            /** Network checker */
            if (!NetworkChecker.networkCheck(app.baseContext)) {

                /** Showing toast if no internet connection */
                viewModelScope.launch(Dispatchers.Main) {
                    Toast.makeText(
                        app.baseContext,
                        AppConstant.NETWORK_CONNECTION,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {

                /** Showing loader */
                eventListener.showLoader()

                /** Creating json object for api request */
                val json = JsonObject()
                json.addProperty(AppConstant.NAME, name.get())
                json.addProperty(AppConstant.USERNAME, userName.get())
                json.addProperty(AppConstant.EMAIL, email.get()?.trim())
                json.addProperty(AppConstant.PASSWORD, password.get())

                /** Calling the loginUseCase */
                val response = signUpUseCase.signUpUseCase(json)

                /** 2 seconds delay 'just to see loader ' */
                delay(1000)

                /** Hiding the loader */
                eventListener.hideLoader()

                when (response) {
                    is ApiResponse.SuccessFul -> {

                        /** Showing toast message */
                        viewModelScope.launch(Dispatchers.Main) {
                            Toast.makeText(
                                app.baseContext,
                                response.successFul?.msg,
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
}
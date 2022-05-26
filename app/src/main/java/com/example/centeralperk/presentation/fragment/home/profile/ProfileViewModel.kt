package com.example.centeralperk.presentation.fragment.home.profile

import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.centeralperk.app.App
import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.domain.repository.EventListener
import com.example.centeralperk.util.AppConstant
import com.example.centeralperk.util.NetworkChecker
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val app: App,
    private val eventListener: EventListener
) : ViewModel() {

    val userProfileImage = ObservableField("")

    val userName = ObservableField("")

    val userBio = ObservableField("")

    /**
     * Calling the useCase userProfile function
     */
    fun userProfile() {

        /** Calling Api in coroutine  */
        viewModelScope.launch(Dispatchers.IO) {

            /** Network checker */
            if (!NetworkChecker.networkCheck(app.baseContext)) {

                /** Showing toast if no internet connection */
                viewModelScope.launch (Dispatchers.Main){
                    Toast.makeText(app.baseContext, AppConstant.NETWORK_CONNECTION, Toast.LENGTH_SHORT).show()
                }
            } else {

                /** Showing loader */
                eventListener.showLoader()

                /** Calling the loginUseCase */
                val response = loginUseCase.loginUseCase(json)

                /** 2 seconds delay 'just to see loader ' */
                delay(1000)

                /** Hiding the loader */
                eventListener.hideLoader()

                when (response) {
                    is ApiResponse.SuccessFul -> {


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
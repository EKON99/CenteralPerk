package com.example.centeralperk.presentation.fragment.home

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.centeralperk.app.App
import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.domain.repository.EventListener
import com.example.centeralperk.domain.usecase.HomeUseCase
import com.example.centeralperk.util.AppConstant
import com.example.centeralperk.util.NetworkChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val home: HomeUseCase,
    private val eventListener: EventListener,
    private val app: App
) : ViewModel() {

    var page = 2

    /** LoaderMutableStateFlow */
    val loaderMutableState = MutableStateFlow(false)

    /**
     * Calling homeUseCase userFeed function
     */
    fun getUserFeed() {

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

                /** Calling the loginUseCase */
                val response = home.homeUserFeed(page.toString(), app.getAuthToken())

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

    /**
     * Calling homeUseCase startUserFeed function
     */
    fun getStartUserFeed() {

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

                /** Changing loader visibility state */
                loaderMutableState.value = true

            } else {

                /** Calling the loginUseCase */
                val response = home.homeUserFeed(page.toString(), app.getAuthToken())

                /** 2 seconds delay 'just to see loader ' */
                delay(1000)

                /** Changing loader visibility state */
                loaderMutableState.value = true

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
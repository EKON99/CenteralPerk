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

    var page: Int? = 1

    /** LoaderMutableStateFlow */
    val refreshLoaderMutableState = MutableStateFlow(false)

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

                /** Calling the loginUseCase */
                val response =
                    page?.toString()?.let { page -> home.homeUserFeed(page, app.getAuthToken()) }

                when (response) {
                    is ApiResponse.SuccessFul -> {

                        /** SwipeRefreshLayout loader visibility */
                        refreshLoaderMutableState.value = true

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
                    else -> {}
                }
            }
        }
    }
}
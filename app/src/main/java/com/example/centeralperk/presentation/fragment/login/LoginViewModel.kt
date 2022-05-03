package com.example.centeralperk.presentation.fragment.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.centeralperk.data.source.ApiResponse
import com.example.centeralperk.domain.repository.EventListener
import com.example.centeralperk.domain.repository.NetworkRepo
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val networkRepoImp: NetworkRepo,
    private val eventListener: EventListener
) : ViewModel() {

    val emailOrUserName = ObservableField("")

    val password = ObservableField("")

    // Password visibility StateFlow
    private val visibilityMutableState = MutableStateFlow(false)
    val visibility = visibilityMutableState.asStateFlow()

    /**
     * Changing the passwordVisibility state
     **/
    fun passwordVisibility() {
        visibilityMutableState.value = visibilityMutableState.value.not()
    }

    /**
     * Calling login function
     */
    fun login() {

        viewModelScope.launch(Dispatchers.IO) {
            val json = JsonObject()
            json.addProperty("email", emailOrUserName.get())
            json.addProperty("password", password.get())

            val response = networkRepoImp.login(json)

            when (response) {
                is ApiResponse.SuccessFul -> {

                }
                is ApiResponse.ApiError<*> -> {}
                is ApiResponse.UnKnownError<*> -> {}
            }

        }

    }
}
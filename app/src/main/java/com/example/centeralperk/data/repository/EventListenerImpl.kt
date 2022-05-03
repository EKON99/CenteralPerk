package com.example.centeralperk.data.repository

import com.example.centeralperk.domain.repository.EventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventListenerImpl : EventListener {

    private val loaderVisibilityMutableStateFlow = MutableStateFlow(false)

    /**
     * Changing loader visibility state to true
     **/
    override suspend fun showLoader() {
        loaderVisibilityMutableStateFlow.value = true
    }

    /**
     * Changing loader visibility state to false
     */
    override suspend fun hideLoader() {
        loaderVisibilityMutableStateFlow.value = false
    }

    /**
     * Getting loader visibility state
     */
    override fun loaderVisibilityState(): StateFlow<Boolean> {
        return loaderVisibilityMutableStateFlow.asStateFlow()
    }
}
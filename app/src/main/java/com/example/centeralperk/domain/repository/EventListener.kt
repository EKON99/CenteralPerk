package com.example.centeralperk.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface EventListener {

    /**
     * Changing loader visibility state to true
     **/
    fun showLoader()

    /**
     * Changing loader visibility state to false
     */
    fun hideLoader()

    /**
     * Getting loader visibility state
     */
    fun loaderVisibilityState(): StateFlow<Boolean>
}
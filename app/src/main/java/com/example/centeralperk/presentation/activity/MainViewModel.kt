package com.example.centeralperk.presentation.activity

import androidx.lifecycle.ViewModel
import com.example.centeralperk.domain.repository.EventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val eventListener: EventListener
) : ViewModel() {

    /** Check dialog visibility */
    var dialogBoxOpenState = false

    /**
     * Getting the loader state from the eventListener
     * @return StateFLow
     */
    fun loaderState(): StateFlow<Boolean> {
        return eventListener.loaderVisibilityState()
    }
}
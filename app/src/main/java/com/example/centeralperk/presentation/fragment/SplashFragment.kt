package com.example.centeralperk.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.centeralperk.app.App
import com.example.centeralperk.databinding.FragmentSplashBinding
import com.example.centeralperk.util.AppConstant
import com.example.centeralperk.util.PreferenceDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashFragment : Fragment() {

    @Inject
    lateinit var app: App

    @Inject
    lateinit var preferenceDataStore: PreferenceDataStore

    /** Data binding component */
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /** Data binding */
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch (Dispatchers.IO){
            preferenceDataStore.read(AppConstant.AUTH_TOKEN).collect { authToken ->

                authToken?.let { token ->
                    if (token.isEmpty()){

                        /** Navigating to login fragment*/
                        return@collect
                    }
                    /** Setting authToken in application class */
                    app.setAuthToken(token)

                    /** Navigating to home fragment*/
                }
            }
        }
    }
}
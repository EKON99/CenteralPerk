package com.example.centeralperk.presentation.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.centeralperk.R
import com.example.centeralperk.app.App
import com.example.centeralperk.databinding.FragmentSplashBinding
import com.example.centeralperk.util.AppConstant
import com.example.centeralperk.util.PreferenceDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
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

        var authenticationToken = ""

        /** Getting the authToken fromt the preference dataStore
         * Storing the authToken in authenticationToken variable
         * */
        lifecycleScope.launch(Dispatchers.IO) {
            preferenceDataStore.read(AppConstant.AUTH_TOKEN).collect { authToken ->
                authToken?.let { token ->
                    authenticationToken = token
                }
            }
        }

        if (authenticationToken == "") {
            /** Navigating to login fragment*/
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)

        } else {
            /** Setting authToken in application class */
            app.setAuthToken(authenticationToken)

            /** Navigating to home fragment*/
            findNavController().navigate(
                R.id.action_splashFragment_to_homeFragment
            )
        }
    }
}
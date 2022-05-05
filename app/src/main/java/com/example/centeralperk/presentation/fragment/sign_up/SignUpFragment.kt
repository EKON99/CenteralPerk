package com.example.centeralperk.presentation.fragment.sign_up

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.centeralperk.R
import com.example.centeralperk.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    /** Data binding component */
    private lateinit var binding: FragmentSignUpBinding

    /** ViewModel dependency */
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /** Data binding */
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Xml data variable initialization */
        binding.data = viewModel

        /** Set listener */
        setListener()

        /** SetObservable */
        setObservable()
    }

    /**
     * Set observable
     * */
    private fun setObservable() {

        /**
         * Collecting the password visibility state
         * */
        lifecycleScope.launch {
            viewModel.visibility.collect { visibilityState ->

                if (visibilityState) {
                    binding.etPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()

                    /** Setting the Visibility image */
                    binding.ivVisibility.setImageResource(R.drawable.ic_visibility_off)

                    return@collect
                }
                binding.etPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()

                /** Setting the Visibility image */
                binding.ivVisibility.setImageResource(R.drawable.ic_visiblity_on)
            }
        }
    }

    /**
     * Set listener
     * */
    private fun setListener() {

        /** Calling viewModel passwordVisibility function */
        binding.ivVisibility.setOnClickListener {
            viewModel.passwordVisibility()
        }

        /** Navigating back to login fragment */
        binding.tvLogin.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
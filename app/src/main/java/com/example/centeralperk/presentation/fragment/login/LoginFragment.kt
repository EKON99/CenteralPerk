package com.example.centeralperk.presentation.fragment.login

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
import com.example.centeralperk.databinding.FragmentLoginBinding
import com.example.centeralperk.util.AppConstant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    /** Data binding component */
    private lateinit var binding: FragmentLoginBinding

    /** ViewModel dependency */
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /** Data binding */
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Initializing the xml data variable */
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

        /** Navigating to forgotPassword fragment */
        binding.tvForgotPassword.setOnClickListener {

        }

        /** Navigating to signUp fragment */
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        /** Calling viewModel passwordVisibility function */
        binding.ivVisibility.setOnClickListener {
            viewModel.passwordVisibility()
        }

        /** Calling viewModel login function */
        binding.btnLogin.setOnClickListener {

            /** UserName or email is empty validation */
            if (viewModel.emailOrUserName.get().isNullOrEmpty()) {
                binding.etEmail.error = AppConstant.USER_NAME_OR_EMAIL_EMPTY
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            /** Password is empty validation */
            if (viewModel.password.get().isNullOrEmpty()) {
                binding.etPassword.error = AppConstant.PASSWORD_EMPTY
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }

            /** Calling viewModel Login function */
            viewModel.login()
        }
    }
}
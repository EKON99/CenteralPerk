package com.example.centeralperk.presentation.fragment.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.centeralperk.databinding.FragmentProfileBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    /** Data binding component */
    private lateinit var binding: FragmentProfileBinding

    /** ViewModel dependency */
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /** Data binding */
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Calling the viewModel usreProfile function if userName is not empty */
        if (viewModel.userName.get().isNullOrEmpty()) {
            viewModel.userProfile()
        }

        /** SetObserver */
        setObserver()
    }

    /**
     * SetObserver
     */
    private fun setObserver() {

        /** Collecting userBio visibility state */
        lifecycleScope.launch {
            viewModel.userBioState.collect { userBioVisibility ->

                /** Changing the userBio visibility state */
                binding.tvUserBio.isVisible = userBioVisibility
            }
        }
    }
}
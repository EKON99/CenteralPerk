package com.example.centeralperk.presentation.fragment.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.centeralperk.databinding.FragmentProfileBinding

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

}
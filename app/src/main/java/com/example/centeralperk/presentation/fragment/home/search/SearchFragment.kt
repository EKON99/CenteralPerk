package com.example.centeralperk.presentation.fragment.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.centeralperk.databinding.FragmentSearchBinding
import com.example.centeralperk.presentation.fragment.home.profile.ProfileViewModel

class SearchFragment : Fragment() {

    /** Data binding component */
    private lateinit var binding: FragmentSearchBinding

    /** ViewModel dependency */
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /** Data binding */
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }
}
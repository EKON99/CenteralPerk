package com.example.centeralperk.presentation.fragment.home.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.centeralperk.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {

    /** Data binding component */
    private lateinit var binding: FragmentNotificationBinding

    /** ViewModel dependency */
    private val viewModel: NotificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /** Data binding */
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        return binding.root
    }

}
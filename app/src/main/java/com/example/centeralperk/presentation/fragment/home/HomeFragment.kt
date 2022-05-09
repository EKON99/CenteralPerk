package com.example.centeralperk.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.centeralperk.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    /** Data binding component */
    private lateinit var binding: FragmentHomeBinding

    /** ViewModel dependency */
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /** Data binding */
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Calling viewModel getUserFeed function */
        viewModel.getUserFeed()

        /** Refresh listener */
        swipeRefresh()

        /** SetObserver */
        setObserver()
    }

    /** SetObserver */
    private fun setObserver() {

        /** Collecting loader state*/
        lifecycleScope.launch {
            viewModel.loaderMutableState.collect {
                binding.srlRefresh.isRefreshing = false

                /** Changing swipeRefreshLoader visibilityState */
                viewModel.loaderMutableState.value = false
            }
        }
    }

    /**
     * Swipe refresh
     */
    private fun swipeRefresh() {

        /** SwipeRefreshListener */
        binding.srlRefresh.setOnRefreshListener {

            /** Calling viewModel getStartUserFeed */
            viewModel.page = 1
            viewModel.getStartUserFeed()
        }
    }
}
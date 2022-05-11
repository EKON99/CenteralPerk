package com.example.centeralperk.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.centeralperk.databinding.FragmentHomeBinding
import com.example.centeralperk.presentation.fragment.home.adapter.HomeFragmentUserFeedAdapter
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

        /** RecyclerView of userFeed */
        userFeedRecyclerView()
    }

    /**
     * UserFeed RecyclerView
     * */
    private fun userFeedRecyclerView() {

        val adapter = HomeFragmentUserFeedAdapter()
        binding.rvUserFeed.adapter = adapter

        binding.rvUserFeed.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {

                    if (viewModel.page != null) {
                        binding.cvLoader.visibility = VISIBLE
                        viewModel.getUserFeed()
                    } else {
                        Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    /** SetObserver */
    private fun setObserver() {

        /** Collecting refresh loader state*/
        lifecycleScope.launch {
            viewModel.refreshLoaderMutableState.collect {
                binding.srlRefresh.isRefreshing = false

                /** Changing swipeRefreshLoader visibilityState */
                viewModel.refreshLoaderMutableState.value = false
            }
        }

        /** Collecting newUserFeed loader state*/
        lifecycleScope.launch {
            viewModel.newUserFeedLoaderMutableState.collect {
                binding.cvLoader.visibility = GONE

                /** Changing swipeRefreshLoader visibilityState */
                viewModel.newUserFeedLoaderMutableState.value = false
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
            viewModel.getUserFeed()
        }
    }
}
package com.example.centeralperk.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.centeralperk.R
import com.example.centeralperk.databinding.FragmentHomeBinding
import com.example.centeralperk.presentation.fragment.home.notification.NotificationFragment
import com.example.centeralperk.presentation.fragment.home.profile.ProfileFragment
import com.example.centeralperk.presentation.fragment.home.search.SearchFragment
import com.example.centeralperk.presentation.fragment.home.user_feed.adapter.UserFeed
import dagger.hilt.android.AndroidEntryPoint

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

        /** Default fragment */
        setFragment(UserFeed())

        /** SetListener */
        setListener()
    }

    /**
     * SetListener
     */
    private fun setListener() {

        /** BottomNavigationBar item click listener */
        binding.bnvHome.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> setFragment(UserFeed())
                R.id.search -> setFragment(SearchFragment())
                R.id.notification -> setFragment(NotificationFragment())
                R.id.profile -> setFragment(ProfileFragment())
            }
            return@setOnItemSelectedListener true
        }
    }

    /**
     * Setting fragment in frameLayout
     * @param fragment
     */
    private fun setFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction().replace(R.id.flHome, fragment).commit()
    }
}
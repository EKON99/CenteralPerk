package com.example.centeralperk.presentation.fragment.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.centeralperk.R
import com.example.centeralperk.databinding.FragmentSignUpBinding
import com.example.centeralperk.presentation.fragment.sign_up.viewpager_and_viewpager_fragment.SignUpViewPager
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    // Data binding component
    private lateinit var binding: FragmentSignUpBinding

    // ViewModel dependency
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Data binding
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Xml data variable initialization
        binding.data = viewModel

        // Set listener
        setListener()

        // TabLayoutAndViewPager
        tabLayoutAndViewPager()
    }

    /**
     * TabLayout and viewPager
     * */
    private fun tabLayoutAndViewPager() {

        val viewPager = binding.viewPager

        // ViewPagerAdapter
        viewPager.adapter = SignUpViewPager(childFragmentManager, lifecycle)

        // TabLayout
        val tabLayout = binding.tbSignUp

        // TabLayout mediator
        TabLayoutMediator(tabLayout, viewPager) { tab, _ ->
            tab.setText(R.string.phone_number)
            tab.setText(R.string.email_address)
        }.attach()
    }

    /**
     * Set listener
     * */
    private fun setListener() {

    }
}
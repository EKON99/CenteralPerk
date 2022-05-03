package com.example.centeralperk.presentation.fragment.sign_up.viewpager_and_viewpager_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.centeralperk.presentation.fragment.sign_up.viewpager_and_viewpager_fragment.email.EmailFragment
import com.example.centeralperk.presentation.fragment.sign_up.viewpager_and_viewpager_fragment.phone_number.PhoneNumberFragment

private const val numberOfTab = 2

class SignUpViewPager(fragmentManager: FragmentManager, lifeCycle: Lifecycle) :

    FragmentStateAdapter(fragmentManager, lifeCycle) {
    override fun getItemCount(): Int {
        return numberOfTab
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> EmailFragment()
            else -> PhoneNumberFragment()
        }
    }
}
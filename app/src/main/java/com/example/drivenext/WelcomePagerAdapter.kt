package com.example.drivenext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class WelcomePagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WelcomeFragment1()
            1 -> WelcomeFragment2()
            2 -> WelcomeFragment3()
            else -> WelcomeFragment1()
        }
    }
}
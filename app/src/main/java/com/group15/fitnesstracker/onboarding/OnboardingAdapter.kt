package com.group15.fitnesstracker.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class OnboardingAdapter(fm: FragmentManager?, private val messages: Array<String>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return OnboardingPageFragment.newInstance(position)
    }

    override fun getCount(): Int = messages.size
}
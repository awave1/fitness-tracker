package com.group15.fitnesstracker.onboarding

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class OnboardingAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    private val PAGES = 3

    override fun getItem(position: Int) = OnboardingPageFragment.newInstance(position)
    override fun getCount(): Int = PAGES
}
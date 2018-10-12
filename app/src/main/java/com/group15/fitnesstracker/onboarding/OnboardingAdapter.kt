package com.group15.fitnesstracker.onboarding

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

const val PAGES = 3

class OnboardingAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int) = OnboardingPageFragment.newInstance(position)
    override fun getCount(): Int = PAGES
}
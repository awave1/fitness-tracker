package com.awave.wrkout.onboarding

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class OnboardingAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val PAGES = 3

    override fun getItem(position: Int) = OnboardingPageFragment.newInstance(position)
    override fun getCount(): Int = PAGES
}
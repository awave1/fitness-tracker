package com.group15.fitnesstracker.onboarding

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class OnboardingAdapter(fm: FragmentManager?, private val messages: Array<String>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = OnboardingPageFragment.newInstance(messages[position], position == this.count - 1)
    override fun getCount(): Int = messages.size
}
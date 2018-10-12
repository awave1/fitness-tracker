package com.group15.fitnesstracker.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.group15.fitnesstracker.R
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        onboardingContainer.apply {
            adapter = OnboardingAdapter(supportFragmentManager, resources.getStringArray(R.array.onboarding_messages))
        }

        onboardingPageIndicator.apply { setViewPager(onboardingContainer) }
    }
}

private class OnboardingAdapter(fm: FragmentManager?, private val messages: Array<String>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        var image = 0
        when (position) {
            0 -> image = R.drawable.ic_fitness_24dp
            1 -> image = R.drawable.ic_heart_24dp
            2 -> image = R.drawable.ic_smile_24dp
        }

        return OnboardingPageFragment.newInstance(messages[position], position == this.count - 1, image)
    }
    override fun getCount(): Int = messages.size
}

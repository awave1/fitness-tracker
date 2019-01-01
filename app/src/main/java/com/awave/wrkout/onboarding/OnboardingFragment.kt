package com.awave.wrkout.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.awave.wrkout.R
import kotlinx.android.synthetic.main.fragment_onboarding.*


class OnboardingFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onboardingContainer.apply { adapter = OnboardingAdapter(fragmentManager) }
        onboardingPageIndicator.apply { setViewPager(onboardingContainer) }
    }

    companion object {
        val instance: OnboardingFragment
            get() = OnboardingFragment()
    }
}

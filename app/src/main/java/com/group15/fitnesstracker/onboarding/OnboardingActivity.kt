package com.group15.fitnesstracker.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.group15.fitnesstracker.R

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        supportFragmentManager.beginTransaction()
                .replace(R.id.onboardingFragmentContainer, OnboardingFragment.instance)
                .commit()
    }
}
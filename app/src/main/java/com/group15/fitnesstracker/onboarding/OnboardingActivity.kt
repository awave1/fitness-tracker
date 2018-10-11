package com.group15.fitnesstracker.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.group15.fitnesstracker.R
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val messages = resources.getStringArray(R.array.onboarding_messages)
        onboardingContainer?.adapter = OnboardingAdapter(supportFragmentManager, messages)
    }
}
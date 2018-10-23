package com.group15.fitnesstracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.group15.fitnesstracker.dashboard.DashboardFragment
import com.group15.fitnesstracker.onboarding.OnboardingActivity
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        if (!pref.getBoolean(Constants.USER_FIRST_TIME, true)) {
            setContentView(R.layout.activity_main)
            setSupportActionBar(toolbar)

            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, DashboardFragment.instance)
                    .addToBackStack(null)
                    .commit()
        } else {
            startActivity(Intent(this, OnboardingActivity::class.java))
        }

    }
}
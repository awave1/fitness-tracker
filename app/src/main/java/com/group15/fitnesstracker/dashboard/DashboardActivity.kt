package com.group15.fitnesstracker.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.group15.fitnesstracker.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)

        dashboardNav.selectedItemId = R.id.navigation_workout
    }
}
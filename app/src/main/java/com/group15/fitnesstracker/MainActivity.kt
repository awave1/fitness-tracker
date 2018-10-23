package com.group15.fitnesstracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.group15.fitnesstracker.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, DashboardFragment.instance)
                .addToBackStack(null)
                .commit()
    }
}
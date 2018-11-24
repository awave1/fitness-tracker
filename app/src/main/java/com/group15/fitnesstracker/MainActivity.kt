package com.group15.fitnesstracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.group15.fitnesstracker.dashboard.DashboardFragment
import com.group15.fitnesstracker.onboarding.OnboardingActivity
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        if (pref.getBoolean(Constants.USER_LOGGED_IN, false) && pref.getInt(Constants.CURRENT_USER_ID, -1) != -1) {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.toolbar_menu_logout -> {
                Toast.makeText(this, getString(R.string.logout_message), Toast.LENGTH_LONG).show()

                val sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
                sharedPref.edit().putBoolean(Constants.USER_LOGGED_IN, false).apply()
                startActivity(Intent(this, OnboardingActivity::class.java))
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
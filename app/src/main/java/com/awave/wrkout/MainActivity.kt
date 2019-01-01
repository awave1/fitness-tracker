package com.awave.wrkout

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.awave.wrkout.dashboard.DashboardFragment
import com.awave.wrkout.onboarding.OnboardingFragment
import com.awave.wrkout.util.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val pref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        if (pref.getBoolean(Constants.USER_LOGGED_IN, false) && pref.getInt(Constants.CURRENT_USER_ID, -1) != -1) {
            supportActionBar?.show()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, DashboardFragment.instance)
                    .commit()
        } else {
            supportActionBar?.hide()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, OnboardingFragment.instance)
                    .commit()
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
                sharedPref.edit()
                        .putInt(Constants.CURRENT_USER_ID, -1)
                        .putBoolean(Constants.USER_LOGGED_IN, false)
                        .putBoolean(Constants.IS_TRAINER, false)
                        .apply()

                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, OnboardingFragment.instance)
                        .commit()

                supportActionBar?.hide()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }
}
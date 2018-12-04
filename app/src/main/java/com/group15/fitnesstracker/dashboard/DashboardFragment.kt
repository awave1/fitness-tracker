package com.group15.fitnesstracker.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.dashboard.history.HistoryFragment
import com.group15.fitnesstracker.dashboard.profile.ProfileFragment
import com.group15.fitnesstracker.dashboard.schedule.ScheduleFragment
import com.group15.fitnesstracker.dashboard.tracker.TrackerFragment
import com.group15.fitnesstracker.dashboard.workout.WorkoutFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dashboardNav.selectedItemId = R.id.navigation_workout

        dashboardNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_profile -> {
                    childFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, ProfileFragment())
                            .commit()

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_history -> {
                    childFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, HistoryFragment())
                            .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_workout -> {
                    childFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, WorkoutFragment())
                            .commit()

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_schedule -> {
                    childFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, ScheduleFragment())
                            .commit()

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_tracker -> {
                    childFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, TrackerFragment())
                            .commit()

                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener true
            }
        }
    }

    companion object {
        val instance: DashboardFragment
            get() = DashboardFragment()
    }
}
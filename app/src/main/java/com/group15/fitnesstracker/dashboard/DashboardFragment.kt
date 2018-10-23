package com.group15.fitnesstracker.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.dashboard.schedule.ScheduleFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dashboardNav.selectedItemId = R.id.navigation_workout
    }

    companion object {
        val instance: DashboardFragment
            get() = DashboardFragment()
    }
}
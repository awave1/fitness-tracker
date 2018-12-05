package com.group15.fitnesstracker.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.customViews.NoScrollViewPager
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
        setupViewPager(dashboardViewPager)

        dashboardNav.selectedItemId = R.id.navigation_workout
        dashboardViewPager.currentItem = 2

        dashboardNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_profile -> {
                    dashboardViewPager.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_history -> {
                    dashboardViewPager.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_workout -> {
                    dashboardViewPager.currentItem = 2
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_schedule -> {
                    dashboardViewPager.currentItem = 3
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_tracker -> {
                    dashboardViewPager.currentItem = 4
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener true
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupViewPager(viewPager: NoScrollViewPager) {
        val adapter = BottomBarViewPagerAdapter(childFragmentManager)
        adapter.addFragments(
                ProfileFragment(),
                HistoryFragment(),
                WorkoutFragment(),
                ScheduleFragment(),
                TrackerFragment()
        )

        viewPager.adapter = adapter
        viewPager.setPagingEnabled(false)
    }

    private inner class BottomBarViewPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        private val fragmentList = mutableListOf<Fragment>()

        override fun getCount(): Int = fragmentList.size
        override fun getItem(position: Int): Fragment = fragmentList[position]


        fun addFragments(vararg fragments: Fragment) {
            fragmentList.addAll(fragments)
        }
    }

    companion object {
        val instance: DashboardFragment
            get() = DashboardFragment()
    }
}
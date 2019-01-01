package com.awave.wrkout.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.awave.wrkout.R
import com.awave.wrkout.customViews.NoScrollViewPager
import com.awave.wrkout.dashboard.history.HistoryFragment
import com.awave.wrkout.dashboard.profile.ProfileFragment
import com.awave.wrkout.dashboard.schedule.ScheduleFragment
import com.awave.wrkout.dashboard.tracker.TrackerFragment
import com.awave.wrkout.dashboard.workout.WorkoutFragment
import com.awave.wrkout.util.Constants
import kotlinx.android.synthetic.main.fragment_dashboard.*
import timber.log.Timber

class DashboardFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = context?.getSharedPreferences(
                resources.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        )

        val isTrainer = sharedPref?.getBoolean(Constants.IS_TRAINER, false) as Boolean
        setupViewPager(dashboardViewPager, isTrainer)

        if (!isTrainer) {
            dashboardNav.inflateMenu(R.menu.menu_dashboard)
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
                    R.id.navigation_tracker -> {
                        dashboardViewPager.currentItem = 3
                        return@setOnNavigationItemSelectedListener true
                    }
                    else -> return@setOnNavigationItemSelectedListener true
                }
            }
        } else {
            dashboardNav.inflateMenu(R.menu.menu_dashboard_trainer)
            dashboardNav.selectedItemId = R.id.navigation_schedule
            dashboardViewPager.currentItem = 1

            dashboardNav.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_profile -> {
                        dashboardViewPager.currentItem = 0
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.navigation_schedule -> {
                        dashboardViewPager.currentItem = 1
                        return@setOnNavigationItemSelectedListener true
                    }
                    else -> return@setOnNavigationItemSelectedListener true
                }
            }
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupViewPager(viewPager: NoScrollViewPager, trainer: Boolean) {
        val adapter = BottomBarViewPagerAdapter(childFragmentManager)
        if (!trainer) {
            adapter.addFragments(
                    ProfileFragment(),
                    HistoryFragment(),
                    WorkoutFragment(),
                    TrackerFragment()
            )
        } else {
            adapter.addFragments(
                    ProfileFragment(),
                    ScheduleFragment()
            )
        }

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

    override fun onResume() {
        super.onResume()
        Timber.d("resumed")
    }
}
package com.group15.fitnesstracker.dashboard.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.group15.fitnesstracker.R
import kotlinx.android.synthetic.main.fragment_tracker_page.*

private const val NUM_PAGES = 2

class TrackerFragment: Fragment() {
    private lateinit var mPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tracker_page, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(BodyTrackerFragment(), "@string/body")
        pagerAdapter.addFragment(NutrTrackerFragment(), "@string/nutrition")
        tracker_pager.setAdapter = pagerAdapter
    }

    /*
    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            // go back
        } else {
            // Otherwise, select the previous step.
            mPager.currentItem = mPager.currentItem - 1
        }
    }
*/

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val fragmentList : MutableList<Fragment> = ArrayList()
        private val titleList : MutableList<String> = ArrayList()

        override fun getCount(): Int = NUM_PAGES
        override fun getItem(position: Int): Fragment = fragmentList[position]
        override fun getPageTitle(position: Int): CharSequence? = titleList[position]

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }
    }

}

package com.group15.fitnesstracker.dashboard.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.group15.fitnesstracker.R

private const val NUM_PAGES = 2

class TrackerFragment: Fragment() {
    private lateinit var mPager: ViewPager
    private lateinit var manager: FragmentManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tracker_page, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = fragmentManager as ViewPager
        manager = fragmentManager as FragmentManager
*/
        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter()
        mPager.adapter = pagerAdapter
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

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment = TrackerViewFragment()
    }

}

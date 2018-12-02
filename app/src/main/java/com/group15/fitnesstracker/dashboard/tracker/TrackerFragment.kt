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
import com.group15.fitnesstracker.dashboard.tracker.createBody.BodyTrackerFragment
import com.group15.fitnesstracker.dashboard.tracker.createBpart.BpartTrackerFragment
import com.group15.fitnesstracker.dashboard.tracker.createNutr.NutrTrackerFragment
import kotlinx.android.synthetic.main.fragment_tracker_page.*

private const val NUM_PAGES = 3

class TrackerFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tracker_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(fragmentManager!!)
        pagerAdapter.addFragment(BodyTrackerFragment(), getString(R.string.body))
        pagerAdapter.addFragment(NutrTrackerFragment(), getString(R.string.nutrition))
        pagerAdapter.addFragment(BpartTrackerFragment(), getString(R.string.bpart))

        tracker_pager.adapter = pagerAdapter

        tracker_tabs.setupWithViewPager(tracker_pager)

        //tracker_pager.addOnPageChangeListener(ViewPager.SimpleOnPageChangeListener ())}
    }

    /*@TODO: Add ListView to display items in database (either body records or nutrition records)
    *       - when clicked -> brought to another fragment view? or alert dialog with update button
    *       - for nutrition records have a micronutrient button as well which displays them
    */
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
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getCount(): Int = NUM_PAGES
        override fun getItem(position: Int): Fragment = fragmentList[position]
        override fun getPageTitle(position: Int): CharSequence? = titleList[position]

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }
    }
}

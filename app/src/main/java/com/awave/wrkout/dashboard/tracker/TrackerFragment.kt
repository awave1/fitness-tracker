package com.awave.wrkout.dashboard.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.awave.wrkout.R
import kotlinx.android.synthetic.main.fragment_tracker_page.*

class TrackerFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tracker_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(BodyTrackerFragment(), getString(R.string.body))
        pagerAdapter.addFragment(NutritionTrackerFragment(), getString(R.string.nutrition))
        pagerAdapter.addFragment(BodyPartTrackerFragment(), getString(R.string.bpart))

        trackerPager.adapter = pagerAdapter
        trackerTabs.setupWithViewPager(trackerPager)
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        private val fragmentList = mutableListOf<Fragment>()
        private val titleList = mutableListOf<String>()

        override fun getCount(): Int = fragmentList.size
        override fun getItem(position: Int): Fragment = fragmentList[position]
        override fun getPageTitle(position: Int): CharSequence? = titleList[position]

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }
    }
}

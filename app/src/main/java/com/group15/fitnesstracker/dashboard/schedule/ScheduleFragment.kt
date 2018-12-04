package com.group15.fitnesstracker.dashboard.schedule

import android.graphics.RectF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alamkanak.weekview.MonthLoader
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewEvent
import com.group15.fitnesstracker.R
import kotlinx.android.synthetic.main.fragment_schedule_page.*

class ScheduleFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weekView.setOnEventClickListener()
        weekView.setMonthChangeListener()
        weekView.setEventLongPressListener()
    }


}
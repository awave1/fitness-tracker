package com.group15.fitnesstracker.dashboard.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewDisplayable
import com.group15.fitnesstracker.R

class ScheduleFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weekView = view.findViewById<WeekView<ScheduleItem>>(R.id.weekView)

        weekView.setOnEventClickListener { any, eventRect ->
            //
        }

        val items: MutableList<ScheduleItem> = mutableListOf()
//        items.add(ScheduleItem())

        weekView.setMonthChangeListener { startDate, endDate -> items as List<WeekViewDisplayable<ScheduleItem>>? }

    }
}

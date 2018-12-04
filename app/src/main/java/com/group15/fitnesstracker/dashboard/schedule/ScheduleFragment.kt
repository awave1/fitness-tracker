package com.group15.fitnesstracker.dashboard.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.R
import kotlinx.android.synthetic.main.fragment_schedule_page.*
import com.alamkanak.weekview.WeekViewDisplayable
import com.alamkanak.weekview.MonthLoader
import com.group15.fitnesstracker.R.id.weekView
import java.util.*


class ScheduleFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weekView.setOnEventClickListener { any, eventRect ->
            //
        }

        weekView.setMonthChangeListener { startDate, endDate -> emptyList() }
    }
}
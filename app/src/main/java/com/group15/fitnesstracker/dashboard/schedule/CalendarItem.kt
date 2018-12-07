package com.group15.fitnesstracker.dashboard.schedule

import android.content.Context
import com.alamkanak.weekview.WeekViewDisplayable
import com.alamkanak.weekview.WeekViewEvent
import java.util.*
import androidx.core.content.ContextCompat
import com.group15.fitnesstracker.R


class CalendarItem(var id: Int, title: String, start: Date, end: Date, val color: Int) : WeekViewDisplayable<CalendarItem> {
    var title: String? = title
    var startTime: Date = start
    var endTime: Date = end

    public val startCalendar = Calendar.getInstance().also { it.time = startTime }
    public val endCalendar = Calendar.getInstance().also { it.time = endTime }

    override fun toWeekViewEvent(): WeekViewEvent<CalendarItem> {

        return WeekViewEvent<CalendarItem>(id.toLong(), title, startCalendar, endCalendar, null, color, false, null)
    }

}
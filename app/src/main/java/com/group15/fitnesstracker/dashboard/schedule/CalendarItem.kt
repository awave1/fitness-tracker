package com.group15.fitnesstracker.dashboard.schedule

import com.alamkanak.weekview.WeekViewDisplayable
import com.alamkanak.weekview.WeekViewEvent
import java.util.*

class CalendarItem: WeekViewDisplayable<CalendarItem> {
    var id: Long = 0
    var title: String? = null
    var startTime: Date = Date()
    var endTime: Date = Date()
    var location: String? = null
    var color: Int = 0

    override fun toWeekViewEvent(): WeekViewEvent<CalendarItem> {
    }

}
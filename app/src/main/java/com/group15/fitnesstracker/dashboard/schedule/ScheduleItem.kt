package com.group15.fitnesstracker.dashboard.schedule

import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewDisplayable
import com.alamkanak.weekview.WeekViewEvent

class ScheduleItem: WeekViewDisplayable<ScheduleItem> {
    override fun toWeekViewEvent(): WeekViewEvent<ScheduleItem> {
        return WeekViewEvent()
    }
}
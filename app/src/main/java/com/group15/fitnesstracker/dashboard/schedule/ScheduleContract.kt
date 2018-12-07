package com.group15.fitnesstracker.dashboard.schedule

import com.alamkanak.weekview.WeekViewDisplayable
import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import java.util.*

interface ScheduleContract {
    interface View: BaseView<Presenter> {
        fun showScheduleItems(items: MutableList<WeekViewDisplayable<CalendarItem>>)
    }

    interface Presenter: BasePresenter {
        fun getEvents(trainerId: Int)
        fun eventClicked()
    }
}
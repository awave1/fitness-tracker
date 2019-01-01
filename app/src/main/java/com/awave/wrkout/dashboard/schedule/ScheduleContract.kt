package com.awave.wrkout.dashboard.schedule

import com.alamkanak.weekview.WeekViewDisplayable
import com.awave.wrkout.base.BasePresenter
import com.awave.wrkout.base.BaseView

interface ScheduleContract {
    interface View: BaseView<Presenter> {
        fun showScheduleItems(items: MutableList<WeekViewDisplayable<CalendarItem>>)
    }

    interface Presenter: BasePresenter {
        fun getEvents(trainerId: Int)
        fun eventClicked()
    }
}
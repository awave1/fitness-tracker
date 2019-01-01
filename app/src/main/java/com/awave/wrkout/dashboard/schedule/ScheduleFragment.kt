package com.awave.wrkout.dashboard.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewDisplayable
import com.alamkanak.weekview.WeekViewEvent
import com.awave.wrkout.R
import com.awave.wrkout.util.Constants
import kotlinx.android.synthetic.main.fragment_schedule_page.*
import java.util.*


class ScheduleFragment: Fragment(), ScheduleContract.View {

    override lateinit var presenter: ScheduleContract.Presenter
    private lateinit var weekView: WeekView<CalendarItem>
    private var scheduleItems: MutableList<WeekViewDisplayable<CalendarItem>> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = SchedulePresenter(this, context)
        return inflater.inflate(R.layout.fragment_schedule_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weekView = view.findViewById(R.id.trainerSchedule)

        val sharedPref = context?.getSharedPreferences(
                context?.resources?.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        )

        val id = sharedPref?.getInt(Constants.CURRENT_USER_ID, -1) as Int

        presenter.getEvents(id)

        weekView.setOnClickListener { presenter.eventClicked() }

        weekView.setMonthChangeListener { startDate, endDate -> scheduleItems }

        addToSchedule.setOnClickListener {
            CreateScheduleItemDialogFactory.create(id, it.context) {
                presenter.getEvents(id)
            }.show()
        }
    }

    override fun showScheduleItems(items: MutableList<WeekViewDisplayable<CalendarItem>>) {
        this.scheduleItems = items
        weekView.notifyDataSetChanged()
    }
}

fun WeekViewEvent<CalendarItem>.eventMatches(year: Int, month: Int): Boolean {
    return startTime.get(Calendar.YEAR) == year && startTime.get(Calendar.MONTH) == month - 1 || endTime.get(Calendar.YEAR) == year && endTime.get(
            Calendar.MONTH) == month - 1
}
package com.group15.fitnesstracker.dashboard.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewDisplayable
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.fragment_schedule_page.*

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

        val id = sharedPref?.getInt(Constants.CURRENT_USER_ID, 0) as Int

        presenter.getEvents(id)

        weekView.setOnClickListener { presenter.eventClicked() }

        weekView.setMonthChangeListener { _, _ -> scheduleItems }

        addToSchedule.setOnClickListener {

        }
    }

    override fun showScheduleItems(items: MutableList<WeekViewDisplayable<CalendarItem>>) {
        this.scheduleItems = items

        weekView.notifyDataSetChanged()
    }
}

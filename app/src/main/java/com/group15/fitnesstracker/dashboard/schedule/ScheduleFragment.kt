package com.group15.fitnesstracker.dashboard.schedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alamkanak.weekview.DateTimeInterpreter
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewDisplayable
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.ScheduleItem
import com.group15.fitnesstracker.util.Constants
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_schedule_page.*
import java.util.*
import java.text.SimpleDateFormat


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

        weekView.dateTimeInterpreter = object : DateTimeInterpreter {
            override fun interpretTime(hour: Int): String {
                var h = hour

                if (hour == 24)
                    h = 0
                else if (hour == 0)
                    h = 0

                return "$h:00"
            }

            override fun interpretDate(date: Calendar?): String {
                val weekdayNameFormat = SimpleDateFormat("EEE", Locale.getDefault())
                var weekday = weekdayNameFormat.format(date?.time)
                val format = SimpleDateFormat(" d/M", Locale.getDefault())

                weekday = weekday.get(0).toString()

                return weekday.toUpperCase() + format.format(date?.time)
            }
        }

        weekView.setOnClickListener { presenter.eventClicked() }

        weekView.setMonthChangeListener { startDate, endDate ->
            return@setMonthChangeListener scheduleItems
        }

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

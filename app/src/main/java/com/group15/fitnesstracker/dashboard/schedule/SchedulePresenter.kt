package com.group15.fitnesstracker.dashboard.schedule

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Transformations.map
import com.alamkanak.weekview.WeekViewDisplayable
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.DbInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

class SchedulePresenter(private val view: ScheduleContract.View, private val context: Context?): ScheduleContract.Presenter {
    init {
        view.presenter = this
    }
    var items: MutableList<WeekViewDisplayable<CalendarItem>> = mutableListOf()

    override fun getEvents(trainerId: Int) {
        context?.let { ctx ->
            DbInjection.provideScheduleItemDao(ctx)
                    .getScheduleItemsForTrainer(trainerId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { scheduleItems ->
                        val color = ContextCompat.getColor(ctx, R.color.eventColor)

                        items = scheduleItems.map {
                            CalendarItem(it.id, "Workout Session", it.from, it.to, color)
                        }.toMutableList()

                        Timber.d("items ${items.size}")

                        view.showScheduleItems(items)
                    }
        }
    }

    override fun eventClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
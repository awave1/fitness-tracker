package com.group15.fitnesstracker.dashboard.schedule

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.Transformations.map
import com.alamkanak.weekview.WeekViewDisplayable
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.DbInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
                        val items = mutableListOf<WeekViewDisplayable<CalendarItem>>()
                        scheduleItems.forEach {
                            DbInjection.provideUserDao(ctx).getById(it.id)
                                    .subscribeOn(Schedulers.io())
                                    .subscribe { user ->

                                        DbInjection.provideWorkoutDao(ctx).getWorkout(it.workoutId).subscribeOn(Schedulers.io())
                                                .subscribe { workout ->
                                                    val title = "${workout.name}: ${user.firstName} ${user.lastName}"
                                                    items.add(CalendarItem(it.id, title, it.from, it.to, color))
                                                }
                                    }
                        }

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
package com.awave.wrkout.dashboard.schedule

import android.content.Context
import androidx.core.content.ContextCompat
import com.alamkanak.weekview.WeekViewDisplayable
import com.awave.wrkout.R
import com.awave.wrkout.db.DbInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

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
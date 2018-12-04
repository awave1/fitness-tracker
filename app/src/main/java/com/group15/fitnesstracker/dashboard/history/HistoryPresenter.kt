package com.group15.fitnesstracker.dashboard.history

import android.content.Context
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.Workout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class HistoryPresenter(private val view: HistoryContract.View, private val context: Context?): HistoryContract.Presenter {
    init {
        view.presenter = this
    }

    private var workouts = listOf<Workout>()

    override fun loadHistoryWorkouts(userId: Int) {
        context?.let { ctx ->
            DbInjection.provideHistoryDao(ctx)
                    .getHistoryForUser(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                    }
        }
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
package com.awave.wrkout.dashboard.history

import android.annotation.SuppressLint
import android.content.Context
import com.awave.wrkout.db.DbInjection
import com.awave.wrkout.db.Workout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HistoryPresenter(private val view: HistoryContract.View, private val context: Context?): HistoryContract.Presenter {
    init {
        view.presenter = this
    }

    private var workouts = listOf<Workout>()
    private var historyIds = HashMap<Int, Int>()

    override fun loadHistoryWorkouts(userId: Int) {
        context?.let { ctx ->
            val historyDao = DbInjection.provideHistoryDao(ctx)
            historyDao.getHistoryForUser(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        workouts = it
                        view.showWorkouts(workouts)

                        workouts.forEach { workout ->
                            historyDao.getRecordingIdForWorkout(workout.workoutId)
                                    .subscribeOn(Schedulers.io())
                                    .subscribe { historyIds[workout.workoutId] = it }
                        }
                    }
        }
    }

    @SuppressLint("CheckResult")
    override fun onBindViewAtPosition(position: Int, view: HistoryViewHolder, userId: Int) {
        val workout = workouts[position]

        view.setWorkout(workout)
        view.setRecordingId(historyIds[workout.workoutId]!!)
        view.setUserId(userId)

        view.showName(workout.name)
        view.showDescription(workout.routineDescription)
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
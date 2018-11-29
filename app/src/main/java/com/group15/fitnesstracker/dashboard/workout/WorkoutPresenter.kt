package com.group15.fitnesstracker.dashboard.workout

import android.content.Context
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.Workout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class WorkoutPresenter(private val view: WorkoutContract.View, private val context: Context?): WorkoutContract.Presenter {
    init {
        view.presenter = this
    }

    private var workouts = listOf<Workout>()

    override fun onBindWorkoutViewAtPosition(position: Int, view: WorkoutContract.WorkoutItemView) {
        val workout = workouts.get(position)
        view.setTitle(workout.name)
        view.setDescription(workout.routineDescription)
    }

    override fun loadWorkouts() {
        context?.let { ctx ->
            DbInjection.provideWorkoutDao(ctx).getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Timber.d("workouts populated (size ${it.size})")

                        workouts = it
                        view.showWorkouts(workouts)
                    }
        }
    }

    override fun start() {
    }

}
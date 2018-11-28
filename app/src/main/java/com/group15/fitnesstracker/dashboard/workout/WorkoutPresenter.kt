package com.group15.fitnesstracker.dashboard.workout

import android.content.Context
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.Workout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WorkoutPresenter(private val context: Context?): WorkoutContract.Presenter {
    private lateinit var workouts: List<Workout>

    override fun getItemCount(): Int = workouts.size

    override fun onBindWorkoutViewAtPosition(position: Int, view: WorkoutContract.WorkoutItemView) {
        val workout = workouts.get(position)
        view.setTitle(workout.name)
        view.setDescription(workout.routineDescription)
    }

    override fun start() {
        context?.let { ctx ->
            DbInjection.provideWorkoutDao(ctx).getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { workouts = it }
        }
    }

}
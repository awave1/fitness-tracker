package com.group15.fitnesstracker.dashboard.workout

import android.content.Context
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.Workout

class WorkoutPresenter(private val context: Context?): WorkoutContract.Presenter {

    private lateinit var workouts: Array<Workout>

    override fun getItemCount(): Int = workouts.size

    override fun onBindWorkoutViewAtPosition(position: Int, view: WorkoutContract.WorkoutItemView) {
        val workout = workouts.get(position)
        view.setTitle(workout.name)
        view.setDescription(workout.routineDescription)
    }

    override fun start() {
        context?.let {
//            DbInjection.provideWorkoutDao(context)
        }
    }

}
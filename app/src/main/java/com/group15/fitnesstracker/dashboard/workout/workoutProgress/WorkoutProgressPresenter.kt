package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import android.content.Context
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.SetExercise

class WorkoutProgressPresenter(private val view: WorkoutProgressContract.View, private val context: Context?): WorkoutProgressContract.Presenter {
    init {
        view.presenter = this
    }

    private var exercises = listOf<SetExercise>()

    override fun onBindViewAtPosition(position: Int, view: WorkoutProgressContract.ExerciseView) {
        val exercise = exercises[position]
    }

    override fun loadExercises(workoutId: Int) {
        context?.let { ctx ->
            DbInjection.provideWorkoutDao(ctx)
        }
    }

    override fun start() {
    }

}
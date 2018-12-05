package com.group15.fitnesstracker.dashboard.workout.createWorkout

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import com.group15.fitnesstracker.db.SetExercise

interface CreateWorkoutContract {
    interface View: BaseView<Presenter> {
        fun showExercises(items: List<SetExercise>)
    }

    interface Presenter: BasePresenter {
        fun loadAllExercises()
        fun onBindViewAtPosition(position: Int, holder: CreateWorkoutAdapter.CreateWorkoutViewHolder)
    }
}
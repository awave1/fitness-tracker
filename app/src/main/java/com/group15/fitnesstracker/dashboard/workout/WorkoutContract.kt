package com.group15.fitnesstracker.dashboard.workout

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import com.group15.fitnesstracker.db.Workout

interface WorkoutContract {
    interface WorkoutItemView {
        fun setTitle(title: String)
        fun setDescription(description: String)
    }

    interface View: BaseView<Presenter> {
        fun showWorkouts(workouts: List<Workout>)
    }

    interface Presenter: BasePresenter {
        fun onBindWorkoutViewAtPosition(position: Int, view: WorkoutItemView)
        fun loadWorkouts()
    }
}
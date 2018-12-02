package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import com.group15.fitnesstracker.db.SetExercise

interface WorkoutProgressContract {
    interface View: BaseView<Presenter> {
        fun showExercises(exercises: List<SetExercise>)
    }

    interface Presenter: BasePresenter {
        fun onBindViewAtPosition(position: Int, view: ExerciseView)
        fun loadExercises(workoutId: Int)
    }

    interface ExerciseView {
        fun showExerciseName(name: String)
        fun showSets(sets: Int)
    }
}
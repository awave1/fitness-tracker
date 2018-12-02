package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView

interface WorkoutProgressContract {
    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {
        fun onBindViewAtPosition(position: Int, view: ExerciseView)
        fun loadExercises(workoutId: Int)
    }

    interface ExerciseView {

    }
}
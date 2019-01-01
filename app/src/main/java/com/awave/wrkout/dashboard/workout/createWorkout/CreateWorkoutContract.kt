package com.awave.wrkout.dashboard.workout.createWorkout

import com.awave.wrkout.base.BasePresenter
import com.awave.wrkout.base.BaseView
import com.awave.wrkout.db.SetExercise

interface CreateWorkoutContract {
    interface View: BaseView<Presenter> {
        fun showExercises(items: List<SetExercise>)
    }

    interface Presenter: BasePresenter {
        fun loadAllExercises()
        fun createWorkout(name: String, description: String, onComplete: () -> Unit)
        fun onBindViewAtPosition(position: Int, holder: CreateWorkoutAdapter.CreateWorkoutViewHolder)
    }
}
package com.awave.wrkout.dashboard.workout

import androidx.fragment.app.FragmentManager
import com.awave.wrkout.base.BasePresenter
import com.awave.wrkout.base.BaseView
import com.awave.wrkout.db.Workout

interface WorkoutContract {
    interface WorkoutItemView {
        fun setTitle(title: String)
        fun setDescription(description: String)
        fun setOnClickListener(fragmentManager: FragmentManager?, workout: Workout)
    }

    interface View: BaseView<Presenter> {
        fun showWorkouts(workouts: List<Workout>)
    }

    interface Presenter: BasePresenter {
        fun onBindWorkoutViewAtPosition(position: Int, view: WorkoutItemView)
        fun loadWorkouts()
    }
}
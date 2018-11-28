package com.group15.fitnesstracker.dashboard.workout

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView

interface WorkoutContract {
    interface WorkoutItemView {
        fun setTitle(title: String)
        fun setDescription(description: String)
    }

    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {
        fun getItemCount(): Int
        fun onBindWorkoutViewAtPosition(position: Int, view: WorkoutItemView)
    }
}
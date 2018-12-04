package com.group15.fitnesstracker.dashboard.history

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import com.group15.fitnesstracker.db.Workout

interface HistoryContract {
    interface View: BaseView<Presenter> {
        fun showWorkouts(workouts: List<Workout>)
    }

    interface Presenter: BasePresenter {
        fun loadHistoryWorkouts(userId: Int)
    }
}
package com.group15.fitnesstracker.dashboard.history

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import com.group15.fitnesstracker.db.Workout

interface HistoryContract {
    interface View: BaseView<Presenter> {
        fun showWorkouts(workouts: List<Workout>)
    }

    interface HistoryItemView {
        fun showName(name: String)
        fun showDescription(description: String)
    }

    interface Presenter: BasePresenter {
        fun loadHistoryWorkouts(userId: Int)
        fun onBindViewAtPosition(position: Int, view: HistoryViewHolder)
    }
}
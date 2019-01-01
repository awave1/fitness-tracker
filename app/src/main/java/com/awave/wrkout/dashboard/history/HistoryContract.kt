package com.awave.wrkout.dashboard.history

import com.awave.wrkout.base.BasePresenter
import com.awave.wrkout.base.BaseView
import com.awave.wrkout.db.Workout

interface HistoryContract {
    interface View: BaseView<Presenter> {
        fun showWorkouts(workouts: List<Workout>)
    }

    interface HistoryItemView {
        fun showName(name: String)
        fun showDescription(description: String)
        fun setWorkout(workout: Workout)
        fun setUserId(userId: Int)
        fun setRecordingId(recordingId: Int)
    }

    interface Presenter: BasePresenter {
        fun loadHistoryWorkouts(userId: Int)
        fun onBindViewAtPosition(position: Int, view: HistoryViewHolder, userId: Int)
    }
}
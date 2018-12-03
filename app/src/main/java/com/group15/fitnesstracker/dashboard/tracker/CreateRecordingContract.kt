package com.group15.fitnesstracker.dashboard.tracker

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import com.group15.fitnesstracker.db.BodyMeasureRecording

interface CreateRecordingContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter {
        fun createBodyRecording(bodyFat: String?, bodyWeight: String?, userId: Int?, callback: (BodyMeasureRecording) -> Unit)
        fun loadBodyRecordings(userId: Int?)
    }

    interface BodyTrackerView: View {
        fun showBodyRecordings(recordings: MutableList<BodyMeasureRecording>)
    }
}
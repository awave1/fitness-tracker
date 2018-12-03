package com.group15.fitnesstracker.dashboard.tracker

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import com.group15.fitnesstracker.db.BodyMeasureRecording
import com.group15.fitnesstracker.db.NutritionRecording

interface CreateRecordingContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter {
        fun createBodyRecording(bodyFat: String?,
                                bodyWeight: String?,
                                userId: Int?, callback: (BodyMeasureRecording) -> Unit)
        fun createNutritionRecording(calories: Double?,
                                              protein: Double?,
                                              carbohydrate: Double?,
                                              fat: Double?,
                                              userId: Int?, callback: (NutritionRecording) -> Unit)

        fun loadBodyRecordings(userId: Int?)
        fun loadNutritionRecordings(userId: Int?)
        fun loadBodyPartRecordings(userId: Int?)
    }

    interface BodyTrackerView: View {
        fun showBodyRecordings(recordings: MutableList<BodyMeasureRecording>)
    }

    interface NutritionTrackerView: View {
        fun showNutritionRecordings(recordings: MutableList<NutritionRecording>)
    }

    interface BodyPartTrackerView: View {
        fun showBodyPartRecordings(recordings: MutableList<BodyMeasureRecording>)
    }
}
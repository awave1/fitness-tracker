package com.awave.wrkout.dashboard.tracker

import com.awave.wrkout.base.BasePresenter
import com.awave.wrkout.base.BaseView
import com.awave.wrkout.db.BodyMeasureRecording
import com.awave.wrkout.db.BodyPartMeasureRecording
import com.awave.wrkout.db.MicronutrientRecording
import com.awave.wrkout.db.NutritionRecording

interface CreateRecordingContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter {
        fun createBodyRecording(bodyFat: String?,
                                bodyWeight: String?,
                                userId: Int?,
                                callback: (BodyMeasureRecording) -> Unit)
        fun createNutritionRecording(calories: Double?,
                                     protein: Double?,
                                     carbohydrate: Double?,
                                     fat: Double?,
                                     userId: Int?,
                                     micronutrients: List<MicronutrientRecording>,
                                     callback: (NutritionRecording) -> Unit)
        fun createBodyPartRecording(bodyPart: String,
                                    bodyPartSize: Double?,
                                    userId: Int?,
                                    callback: (BodyPartMeasureRecording) -> Unit)

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
        fun showBodyPartRecordings(recordings: MutableList<BodyPartMeasureRecording>)
    }
}
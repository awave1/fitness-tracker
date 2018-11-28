package com.group15.fitnesstracker.dashboard.tracker.createBpart

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import java.util.*

interface CreateBpartContract {
    interface View: BaseView<Presenter> {
    }

    interface Presenter: BasePresenter {
        fun createNutrition(recordingId: Int, date: Date, bodypart: String, bodypartSize: Double)
    }
}
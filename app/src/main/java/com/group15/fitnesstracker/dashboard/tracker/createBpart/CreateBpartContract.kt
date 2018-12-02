package com.group15.fitnesstracker.dashboard.tracker.createBpart

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import java.util.*

interface CreateBPartContract {
    interface View: BaseView<Presenter> {
    }

    interface Presenter: BasePresenter {
        fun createNutrition(recordingId: Int, date: Date, calories: Double, protein: Double,carbohydrate: Double,fat: Double)
    }
}
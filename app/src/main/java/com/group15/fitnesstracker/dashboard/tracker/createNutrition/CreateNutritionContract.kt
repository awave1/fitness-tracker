package com.group15.fitnesstracker.dashboard.tracker.createNutrition

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import java.util.*

interface CreateNutritionContract {
    interface View: BaseView<Presenter> {
    }

    interface Presenter: BasePresenter {
        fun createNutrition(recordingId: Int, date: Date, calories: Double, protein: Double, carb: Double, fat: Double)
    }
}
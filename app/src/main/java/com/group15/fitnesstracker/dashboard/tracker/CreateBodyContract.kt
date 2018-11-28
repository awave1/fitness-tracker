package com.group15.fitnesstracker.dashboard.tracker

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import java.util.*

interface CreateBodyContract {
    interface View: BaseView<Presenter> {
    }

    interface Presenter: BasePresenter {
        fun createBody(recordingId: Int, date: Date, bodyfat: Double, weight: Double)
    }
}
package com.group15.fitnesstracker.dashboard.tracker

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView

interface CreateRecordingContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter {
        fun createBodyRecording(bodyFat: String?, bodyWeight: String?)
    }
}
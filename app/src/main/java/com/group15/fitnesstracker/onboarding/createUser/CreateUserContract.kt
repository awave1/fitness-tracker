package com.group15.fitnesstracker.onboarding.createUser

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView

interface CreateUserContract {
    interface View: BaseView<Presenter> {
    }

    interface Presenter: BasePresenter {
        fun createUser()
    }
}
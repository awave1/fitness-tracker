package com.group15.fitnesstracker.onboarding.login

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView

interface LoginContract {
    interface View: BaseView<Presenter>{}

    interface Presenter: BasePresenter {
        fun login(username: String, password: String)
    }
}
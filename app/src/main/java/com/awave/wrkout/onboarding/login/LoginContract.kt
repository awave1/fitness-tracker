package com.awave.wrkout.onboarding.login

import com.awave.wrkout.base.BasePresenter
import com.awave.wrkout.base.BaseView

interface LoginContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter {
        fun loginUser(username: String, password: String)
        fun loginTrainer(username: String, password: String)
    }
}
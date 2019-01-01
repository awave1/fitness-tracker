package com.awave.wrkout.onboarding.createUser

import com.awave.wrkout.base.BasePresenter
import com.awave.wrkout.base.BaseView

interface CreateUserContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter {
        fun createUser(username: String, password: String, firstName: String, lastName: String, age: Int, weight: Double)
        fun createTrainer(email: String, password: String, firstName: String, lastName: String)
    }
}
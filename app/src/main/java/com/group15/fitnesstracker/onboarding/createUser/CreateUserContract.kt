package com.group15.fitnesstracker.onboarding.createUser

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView

interface CreateUserContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter {
        fun createUser(username: String, password: String, firstName: String, lastName: String, age: Int, weight: Double)
        fun createTrainer(email: String, password: String, firstName: String, lastName: String)
    }
}
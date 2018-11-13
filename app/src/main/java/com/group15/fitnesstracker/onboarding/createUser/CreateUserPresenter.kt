package com.group15.fitnesstracker.onboarding.createUser

import android.content.Context
import com.group15.fitnesstracker.db.FitnessTrackerDatabase
import com.group15.fitnesstracker.db.User

class CreateUserPresenter(val view: CreateUserContract.View, val context: Context): CreateUserContract.Presenter {
    override fun createUser(firstName: String, lastName: String, age: Int, weight: Double) {
        val user = User(firstName, lastName, age, weight)
        FitnessTrackerDatabase.instance(context)?.userDao()?.insert(user)
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
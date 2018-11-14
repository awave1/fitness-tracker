package com.group15.fitnesstracker.onboarding.createUser

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.group15.fitnesstracker.MainActivity
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.FitnessTrackerDatabase
import com.group15.fitnesstracker.db.User
import com.group15.fitnesstracker.util.Constants
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CreateUserPresenter(val view: CreateUserContract.View, val context: Context): CreateUserContract.Presenter {

    lateinit var db: FitnessTrackerDatabase

    override fun createUser(firstName: String, lastName: String, age: Int, weight: Double) {
        val user = User(firstName, lastName, age, weight)

        Observable.just(db)
                .subscribeOn(Schedulers.io())
                .subscribe { db.userDao().insert(user) }
                .dispose()
    }

    override fun start() {
        db = FitnessTrackerDatabase.instance(context)!!
    }
}
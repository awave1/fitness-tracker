package com.group15.fitnesstracker.onboarding.createUser

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.dashboard.DashboardFragment
import com.group15.fitnesstracker.db.User
import com.group15.fitnesstracker.db.dao.UserDao
import com.group15.fitnesstracker.util.Constants
import com.group15.fitnesstracker.util.CryptoUtils
import io.reactivex.schedulers.Schedulers

class CreateUserPresenter(val view: CreateUserContract.View,
                          private val context: Context?,
                          private val fm: FragmentManager?,
                          private val userDao: UserDao): CreateUserContract.Presenter {
    @SuppressLint("CheckResult")
    override fun createUser(username: String, password: String, firstName: String, lastName: String, age: Int, weight: Double) {
        val passHash = CryptoUtils.SHA256Hash(password)
        val user = User(username, passHash, firstName, lastName, age, weight)

        userDao.insert(user)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    val sharedPref = context?.getSharedPreferences(
                            context.resources.getString(R.string.preference_file_key),
                            Context.MODE_PRIVATE
                    )

                    sharedPref?.edit()
                            ?.putBoolean(Constants.USER_LOGGED_IN, true)
                            ?.putInt(Constants.CURRENT_USER_ID, user.id)
                            ?.apply()

                    fm?.beginTransaction()
                            ?.replace(R.id.container, DashboardFragment.instance)
                            ?.commit()
                }
    }

    override fun start() {
    }
}
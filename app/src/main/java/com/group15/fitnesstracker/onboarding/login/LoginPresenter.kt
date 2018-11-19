package com.group15.fitnesstracker.onboarding.login

import android.annotation.SuppressLint
import android.content.Context
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.base.BaseView
import com.group15.fitnesstracker.db.dao.UserDao
import com.group15.fitnesstracker.util.Constants
import io.reactivex.schedulers.Schedulers

class LoginPresenter(val view: LoginContract.View, private val context: Context, private val userDao: UserDao): LoginContract.Presenter {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @SuppressLint("CheckResult")
    override fun login(username: String, password: String) {
        // @TODO: implement password handling as well

        userDao.getByUsername(username)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    val sharedPref = context.getSharedPreferences(
                            context.getString(R.string.preference_file_key),
                            Context.MODE_PRIVATE
                    )

                    sharedPref.edit()
                            .putBoolean(Constants.USER_LOGGED_IN, true)
                            .putInt(Constants.CURRENT_USER_ID, it.id)
                            .apply()
                }
    }
}

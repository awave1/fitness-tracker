package com.group15.fitnesstracker.onboarding.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentManager
import com.group15.fitnesstracker.MainActivity
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.dashboard.DashboardFragment
import com.group15.fitnesstracker.db.dao.UserDao
import com.group15.fitnesstracker.util.Constants
import com.group15.fitnesstracker.util.CryptoUtils
import io.reactivex.schedulers.Schedulers

class LoginPresenter(val view: LoginContract.View,
                     private val context: Context?,
                     private val fm: FragmentManager?,
                     private val userDao: UserDao): LoginContract.Presenter {
    @SuppressLint("CheckResult")
    override fun login(username: String, password: String) {
        val passHash = CryptoUtils.SHA256Hash(password)

        userDao.getByUsername(username, passHash)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    val sharedPref = context?.getSharedPreferences(
                            context.getString(R.string.preference_file_key),
                            Context.MODE_PRIVATE
                    )

                    sharedPref?.edit()
                            ?.putBoolean(Constants.USER_LOGGED_IN, true)
                            ?.putInt(Constants.CURRENT_USER_ID, it.id)
                            ?.apply()


                    fm?.beginTransaction()
                            ?.replace(R.id.container, DashboardFragment.instance)
                            ?.commit()
                }
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

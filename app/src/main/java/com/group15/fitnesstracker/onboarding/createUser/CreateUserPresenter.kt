package com.group15.fitnesstracker.onboarding.createUser

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.dashboard.DashboardFragment
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.Trainer
import com.group15.fitnesstracker.db.User
import com.group15.fitnesstracker.db.dao.UserDao
import com.group15.fitnesstracker.util.Constants
import com.group15.fitnesstracker.util.CryptoUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CreateUserPresenter(val view: CreateUserContract.View,
                          private val context: Context?,
                          private val fm: FragmentManager?,
                          private val userDao: UserDao): CreateUserContract.Presenter {
    @SuppressLint("CheckResult")
    override fun createUser(username: String, password: String, firstName: String, lastName: String, age: Int, weight: Double) {
        val passHash = CryptoUtils.SHA256Hash(password)
        val user = User(
                username = username,
                password = passHash,
                firstName = firstName,
                lastName = lastName,
                age = age,
                weight = weight
        )

        userDao.insert(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val sharedPref = context?.getSharedPreferences(
                            context.resources.getString(R.string.preference_file_key),
                            Context.MODE_PRIVATE
                    )

                    sharedPref?.edit()
                            ?.putBoolean(Constants.USER_LOGGED_IN, true)
                            ?.putInt(Constants.CURRENT_USER_ID, it.toInt())
                            ?.apply()

                    fm?.beginTransaction()
                            ?.replace(R.id.container, DashboardFragment.instance)
                            ?.commit()
                }, {
                    Toast.makeText(context, "Failed to create user!", Toast.LENGTH_LONG).show()
                    Timber.e(it, "Failed to create user!")
                })
    }

    override fun createTrainer(email: String, password: String, firstName: String, lastName: String) {
        val passHash = CryptoUtils.SHA256Hash(password)
        val trainer = Trainer(
                email = email,
                password = passHash,
                firstName = firstName,
                lastName = lastName
        )

        context?.let { ctx ->
            DbInjection.provideTrainerDao(ctx)
                    .createTrainer(trainer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        val sharedPref = ctx.getSharedPreferences(
                                context.resources.getString(R.string.preference_file_key),
                                Context.MODE_PRIVATE
                        )

                        sharedPref?.edit()
                                ?.putBoolean(Constants.USER_LOGGED_IN, true)
                                ?.putBoolean(Constants.IS_TRAINER, true)
                                ?.putInt(Constants.CURRENT_USER_ID, it.toInt())
                                ?.apply()

                        fm?.beginTransaction()
                                ?.replace(R.id.container, DashboardFragment.instance)
                                ?.commit()
                    }, {
                        Toast.makeText(context, "Failed to create trainer!", Toast.LENGTH_LONG).show()
                        Timber.e(it, "Failed to create trainer!")
                    })
        }
    }

    override fun start() {
    }
}
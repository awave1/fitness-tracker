package com.awave.wrkout.onboarding.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.awave.wrkout.R
import com.awave.wrkout.dashboard.DashboardFragment
import com.awave.wrkout.db.DbInjection
import com.awave.wrkout.db.dao.UserDao
import com.awave.wrkout.util.Constants
import com.awave.wrkout.util.CryptoUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter(val view: LoginContract.View,
                     private val context: Context?,
                     private val fm: FragmentManager?,
                     private val userDao: UserDao): LoginContract.Presenter {
    @SuppressLint("CheckResult")
    override fun loginUser(username: String, password: String) {
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

    override fun loginTrainer(username: String, password: String) {
        val passHash = CryptoUtils.SHA256Hash(password)

        context?.let { ctx ->
            DbInjection.provideTrainerDao(ctx)
                    .getTrainer(username, passHash)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        val sharedPref = ctx.getSharedPreferences(
                                context.getString(R.string.preference_file_key),
                                Context.MODE_PRIVATE
                        )

                        sharedPref?.edit()
                                ?.putBoolean(Constants.USER_LOGGED_IN, true)
                                ?.putBoolean(Constants.IS_TRAINER, true)
                                ?.putInt(Constants.CURRENT_USER_ID, it.id)
                                ?.apply()

                        fm?.beginTransaction()
                                ?.replace(R.id.container, DashboardFragment.instance)
                                ?.commit()
                    }
        }
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

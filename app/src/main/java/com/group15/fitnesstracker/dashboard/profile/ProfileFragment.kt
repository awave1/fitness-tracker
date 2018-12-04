package com.group15.fitnesstracker.dashboard.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.Goal
import com.group15.fitnesstracker.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_profile_page.*
import timber.log.Timber

class ProfileFragment: Fragment(), CreateGoalContract.GoalView {
    override lateinit var presenter: CreateGoalContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = context?.getSharedPreferences(
                context?.resources?.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        )

        val id = sharedPref?.getInt(Constants.CURRENT_USER_ID, -1) as Int
        Timber.d("user id: $id")

        context?.let { c ->
            DbInjection.provideDb(c).userDao().getById(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { profileName.text = getString(R.string.dashboard_user_name, "${it.firstName} ${it.lastName}") }
        }

    }

    override fun showGoals(goals: List<Goal>) {

    }

}

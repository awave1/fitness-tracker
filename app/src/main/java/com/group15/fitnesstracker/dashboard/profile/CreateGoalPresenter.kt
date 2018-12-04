package com.group15.fitnesstracker.dashboard.profile

import android.content.Context
import android.widget.Toast
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.Goal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class CreateGoalPresenter(val view: CreateGoalContract.View, private val context: Context?): CreateGoalContract.Presenter {
    init {
        view.presenter = this
    }

    private var goals = listOf<Goal>()

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createGoal(description: String?, date: Date?, userId: Int?, callback: (Goal) -> Unit) {
        context?.let {
            val goal = Goal(goalDescription = description, completionDate = date, userId = userId)
            DbInjection.provideGoalDao(it)
                    .insertGoal(goal)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe() {
                        Toast.makeText(it, "Saved", Toast.LENGTH_SHORT).show()
                        callback(goal)
                    }
        }
    }

    override fun loadGoals(userId: Int?) {
        userId?.let { id ->
            context?.let { ctx ->
                DbInjection.provideGoalDao(ctx)
                        .getGoalsForUser(userId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            goals = it
                            (view as CreateGoalContract.GoalView).showGoals(it)
                        }
            }
        }
    }

}
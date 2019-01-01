package com.awave.wrkout.dashboard.profile

import android.content.Context
import android.widget.Toast
import com.awave.wrkout.db.DbInjection
import com.awave.wrkout.db.Goal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class ProfilePresenter(val view: ProfileContract.View, private val context: Context?): ProfileContract.Presenter {
    init {
        view.presenter = this
    }

    private var goals = mutableListOf<Goal>()
    var prevDayCals = 0.0
    var prevDayProtein = 0.0
    var prevDayCarbs = 0.0
    var prevDayFat = 0.0

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
                    .subscribe {
                        Toast.makeText(it, "Saved", Toast.LENGTH_SHORT).show()
                        callback(goal)
                    }
        }
    }

    override fun loadGoals(userId: Int?) {
        userId?.let { id ->
            context?.let { ctx ->
                DbInjection.provideGoalDao(ctx)
                        .getGoalsForUser(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            goals.addAll(it)
                            view.showGoals(goals)
                        }
            }
        }
    }

    override fun loadPrevDayStats(userId: Int?) {
        userId?.let { id ->
            context?.let { ctx ->
                DbInjection.provideNutritionRecordingDao(ctx).getDailyStats(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { view.showUserStats(it) }
            }
        }
    }
}
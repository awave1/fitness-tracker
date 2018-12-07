package com.group15.fitnesstracker.dashboard.profile

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import com.group15.fitnesstracker.db.Goal
import com.group15.fitnesstracker.db.dao.UserStats
import java.util.*

interface ProfileContract {
    interface View: BaseView<Presenter> {
        fun showGoals(goals: MutableList<Goal>)
        fun showUserStats(stats: UserStats)
    }

    interface Presenter: BasePresenter {
        fun createGoal(description: String?,
                       date: Date?,
                       userId: Int?,
                       callback: (Goal) -> Unit)

        fun loadGoals(userId: Int?)

        fun loadPrevDayStats(userId: Int?)
    }
}
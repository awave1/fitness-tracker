package com.awave.wrkout.dashboard.profile

import com.awave.wrkout.base.BasePresenter
import com.awave.wrkout.base.BaseView
import com.awave.wrkout.db.Goal
import com.awave.wrkout.db.dao.UserStats
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
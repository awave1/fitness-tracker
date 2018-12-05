package com.group15.fitnesstracker.dashboard.profile

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import com.group15.fitnesstracker.db.Goal
import java.util.*

interface ProfileContract {
    interface View: BaseView<Presenter>

    interface Presenter: BasePresenter {
        fun createGoal(description: String?,
                       date: Date?,
                       userId: Int?,
                       callback: (Goal) -> Unit)

        fun loadGoals(userId: Int?)
    }

    interface GoalView: View {
        fun showGoals(goals: List<Goal>)
    }
}
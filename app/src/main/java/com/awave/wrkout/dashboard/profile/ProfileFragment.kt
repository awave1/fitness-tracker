package com.awave.wrkout.dashboard.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.awave.wrkout.R
import com.awave.wrkout.db.DbInjection
import com.awave.wrkout.db.Goal
import com.awave.wrkout.db.dao.UserStats
import com.awave.wrkout.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_profile_page.*
import timber.log.Timber

class ProfileFragment: Fragment(), ProfileContract.View {
    override lateinit var presenter: ProfileContract.Presenter
    private lateinit var adapter: GoalAdapter<Goal>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = ProfilePresenter(this, context)
        return inflater.inflate(R.layout.fragment_profile_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = context?.getSharedPreferences(
                context?.resources?.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        )

        val id = sharedPref?.getInt(Constants.CURRENT_USER_ID, -1) as Int
        val isTrainer = sharedPref.getBoolean(Constants.IS_TRAINER, false)

        Timber.d("user id: $id")

        if (!isTrainer) {
            presenter.loadPrevDayStats(id)
            presenter.loadGoals(id)

            adapter = GoalAdapter(R.layout.goal_item)
            goalList.layoutManager = LinearLayoutManager(context)
            goalList.adapter = adapter

            context?.let { c ->
                DbInjection.provideUserDao(c).getById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { profileName.text = getString(R.string.dashboard_user_name, "${it.firstName} ${it.lastName}") }
            }

            createGoalBtn.setOnClickListener {
                CreateGoalDialogFactory
                        .create(
                                R.layout.fragment_create_goal,
                                layoutInflater,
                                it.context,
                                onSave = { date, description ->
                                    presenter.createGoal(description, date = date, userId = id) { goal ->
                                        adapter.items.add(goal)
                                        adapter.notifyDataSetChanged()
                                    }
                                },
                                onCancel = { dialog, id -> dialog.cancel() }
                        )
                        .show()
            }
        } else {
            createGoalBtn.visibility = View.GONE
            goalList.visibility = View.GONE
            goals_text_header.visibility = View.GONE

            context?.let { c ->
                DbInjection.provideTrainerDao(c)
                        .getTrainer(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { profileName.text = getString(R.string.dashboard_user_name, "${it.firstName} ${it.lastName}") }
            }
        }

    }

    override fun showGoals(goals: MutableList<Goal>) {
        adapter.items = goals
    }

    override fun showUserStats(stats: UserStats) {
        if (stats.sumCalories > 0 && stats.sumProtein > 0 && stats.sumCarbs > 0 && stats.sumFat > 0) {
            dailyNutritionalInfo.visibility = View.VISIBLE
            dailyNutritionalInfoHeader.visibility = View.VISIBLE
            dailyNutritionalInfoValues.visibility = View.VISIBLE

            noStats.visibility = View.GONE

            calories.text = stats.sumCalories.toString()
            protein.text = stats.sumProtein.toString()
            carbs.text = stats.sumCarbs.toString()
            fat.text = stats.sumFat.toString()
        } else {
            dailyNutritionalInfo.visibility = View.GONE
            dailyNutritionalInfoHeader.visibility = View.GONE
            dailyNutritionalInfoValues.visibility = View.GONE

            noStats.visibility = View.VISIBLE
        }
    }
}

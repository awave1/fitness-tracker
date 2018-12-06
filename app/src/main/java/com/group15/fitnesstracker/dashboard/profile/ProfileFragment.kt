package com.group15.fitnesstracker.dashboard.profile

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputLayout
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.Goal
import com.group15.fitnesstracker.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_profile_page.*
import timber.log.Timber
import java.util.*

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
        Timber.d("user id: $id")

        presenter.loadGoals(id)

        adapter = GoalAdapter(R.layout.goal_item)
        goalList.layoutManager = LinearLayoutManager(context)
        goalList.adapter = adapter

        context?.let { c ->
            DbInjection.provideDb(c).userDao().getById(id)
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
                                presenter.createGoal(description, date = date, userId = id) {goal ->
                                    adapter.items.add(goal)
                                    adapter.notifyDataSetChanged()
                                }
                            },
                            onCancel = { dialog, id -> dialog.cancel() }
                    )
                    .show()
        }

    }

    override fun showGoals(goals: MutableList<Goal>) {
        adapter.items = goals
    }
}

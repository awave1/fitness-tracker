package com.group15.fitnesstracker.dashboard.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.Workout
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.fragment_history_page.*

class HistoryFragment: Fragment(), HistoryContract.View {
    override lateinit var presenter: HistoryContract.Presenter
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = HistoryPresenter(this, context)
        return inflater.inflate(R.layout.fragment_history_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HistoryAdapter(presenter)

        val sharedPref = context?.getSharedPreferences(
                context?.resources?.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        )

        val userId = sharedPref?.getInt(Constants.CURRENT_USER_ID, 0) as Int

        presenter.loadHistoryWorkouts(userId)

        historyList.layoutManager = LinearLayoutManager(context)
        historyList.adapter = adapter
    }

    override fun showWorkouts(workouts: List<Workout>) {
        adapter.items = workouts
    }
}
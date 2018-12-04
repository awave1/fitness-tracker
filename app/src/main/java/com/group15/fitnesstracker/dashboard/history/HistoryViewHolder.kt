package com.group15.fitnesstracker.dashboard.history

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.History
import com.group15.fitnesstracker.db.Schedule
import com.group15.fitnesstracker.db.Workout
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HistoryViewHolder(view: View): RecyclerView.ViewHolder(view), HistoryContract.HistoryItemView {
    private val nameTextView = view.findViewById<TextView>(R.id.workoutName)
    private val descriptionTextView = view.findViewById<TextView>(R.id.workoutDescription)

    private lateinit var workout: Workout
    private var userId: Int = 0

    init {
        view.setOnLongClickListener {
            AlertDialog.Builder(it.context)
                    .setTitle("Delete ${workout.name}?")
                    .setPositiveButton("Delete") { dialog, which ->
                        DbInjection.provideHistoryDao(it.context)
                                .deleteHistories(History(userId, workout.workoutId))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    Toast.makeText(it.context, "Deleted ${workout.name}", Toast.LENGTH_SHORT).show()
                                }
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()

            return@setOnLongClickListener true
        }
    }

    override fun showName(name: String) {
        nameTextView.text = name
    }

    override fun showDescription(description: String) {
        descriptionTextView.text = description
    }

    override fun setWorkout(workout: Workout) {
        this.workout = workout
    }

    override fun setUserId(userId: Int) {
        this.userId = userId
    }
}
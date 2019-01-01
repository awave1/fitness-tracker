package com.awave.wrkout.dashboard.history

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awave.wrkout.R
import com.awave.wrkout.db.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HistoryViewHolder(view: View): RecyclerView.ViewHolder(view), HistoryContract.HistoryItemView {
    private val nameTextView = view.findViewById<TextView>(R.id.workoutName)
    private val descriptionTextView = view.findViewById<TextView>(R.id.workoutDescription)

    private lateinit var workout: Workout
    private var userId: Int = 0
    private var recordingId = 0

    init {
        view.setOnClickListener { card ->
            DbInjection.provideWorkoutExercisesDao(card.context)
                    .getExercises(workout.workoutId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { showExercises(card.context, it) }
        }

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

    private fun showExercises(context: Context, exercises: List<SetExercise>) {
        val view = View.inflate(context, R.layout.completed_sets, null)
        val exerciseList = view.findViewById<RecyclerView>(R.id.exerciseList)
        val adapter = ExerciseAdapter(userId = userId, workoutId = workout.workoutId, recordingId = recordingId)

        exerciseList.layoutManager = LinearLayoutManager(context)
        exerciseList.adapter = adapter

        adapter.items = exercises

        AlertDialog.Builder(context).setView(view).create().show()
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

    override fun setRecordingId(recordingId: Int) {
        this.recordingId = recordingId
    }
}
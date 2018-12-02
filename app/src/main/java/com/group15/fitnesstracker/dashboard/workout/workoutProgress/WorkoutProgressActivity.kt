package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.SetExercise
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.activity_workout_progress.*
import timber.log.Timber

class WorkoutProgressActivity: AppCompatActivity(), WorkoutProgressContract.View {
    override lateinit var presenter: WorkoutProgressContract.Presenter
    private lateinit var adapter: WorkoutProgressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_progress)

        val workoutId = intent.getIntExtra(Constants.WORKOUT_ID, 0)
        Timber.d("workout $workoutId")

        presenter = WorkoutProgressPresenter(this, this)
        presenter.loadExercises(workoutId)

        adapter = WorkoutProgressAdapter(presenter as WorkoutProgressPresenter)

        exerciseList.layoutManager = LinearLayoutManager(this)
        exerciseList.adapter = adapter
    }

    override fun showExercises(exercises: List<SetExercise>) {
        adapter.items = exercises
    }
}
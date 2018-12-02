package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.util.Constants
import kotlinx.android.synthetic.main.activity_workout_progress.*
import timber.log.Timber

class WorkoutProgressActivity: AppCompatActivity(), WorkoutProgressContract.View {
    override lateinit var presenter: WorkoutProgressContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_progress)
        presenter = WorkoutProgressPresenter(this)

        val workoutId = intent.getIntExtra(Constants.WORKOUT_ID, 0)
        Timber.d("workout $workoutId")

        presenter.loadExercises(workoutId)

        exerciseList.layoutManager = LinearLayoutManager(this)
        exerciseList.adapter = WorkoutProgressAdapter(presenter as WorkoutProgressPresenter)
    }
}
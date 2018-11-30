package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.util.Constants
import timber.log.Timber

class WorkoutProgressActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_progress)
        val workoutId = intent.getIntExtra(Constants.WORKOUT_ID, 0)
        Timber.d("workout $workoutId")
    }
}
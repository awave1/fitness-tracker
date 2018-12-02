package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R
import kotlinx.android.synthetic.main.card_exercise.view.*

class WorkoutProgressViewHolder(view: View): RecyclerView.ViewHolder(view), WorkoutProgressContract.ExerciseView {

    private val exerciseName: TextView = view.findViewById(R.id.exerciseName)

    override fun showExerciseName(name: String) {
        exerciseName.text = name
    }
}
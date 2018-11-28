package com.group15.fitnesstracker.dashboard.workout

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R

class WorkoutItemViewHolder(workoutItemView: View) : RecyclerView.ViewHolder(workoutItemView), WorkoutContract.WorkoutItemView {
    private var workoutName: TextView = workoutItemView.findViewById(R.id.workoutName)
    private var workoutDescription: TextView = workoutItemView.findViewById(R.id.workoutDescription)

    override fun setTitle(title: String) {
        workoutName.text = title
    }

    override fun setDescription(description: String) {
        workoutDescription.text = description
    }
}
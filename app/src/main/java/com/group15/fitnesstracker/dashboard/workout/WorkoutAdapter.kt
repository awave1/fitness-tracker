package com.group15.fitnesstracker.dashboard.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R

class WorkoutAdapter(private val presenter: WorkoutPresenter) : RecyclerView.Adapter<WorkoutItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutItemViewHolder {
        return WorkoutItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.card_workout_routine, parent))
    }

    override fun getItemCount(): Int = presenter.getItemCount()

    override fun onBindViewHolder(holder: WorkoutItemViewHolder, position: Int) {
        presenter.onBindWorkoutViewAtPosition(position, holder)
    }
}
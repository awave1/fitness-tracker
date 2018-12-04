package com.group15.fitnesstracker.dashboard.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.Workout

class WorkoutAdapter(private val presenter: WorkoutContract.Presenter) : RecyclerView.Adapter<WorkoutItemViewHolder>() {
    var items: List<Workout> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutItemViewHolder {
        return WorkoutItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.card_workout_routine, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WorkoutItemViewHolder, position: Int) {
        presenter.onBindWorkoutViewAtPosition(position, holder)
    }
}
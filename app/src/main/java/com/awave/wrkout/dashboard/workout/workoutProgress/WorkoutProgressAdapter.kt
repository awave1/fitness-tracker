package com.awave.wrkout.dashboard.workout.workoutProgress

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.awave.wrkout.R
import com.awave.wrkout.db.SetExercise

class WorkoutProgressAdapter(private val presenter: WorkoutProgressPresenter): RecyclerView.Adapter<WorkoutProgressViewHolder>() {
    var items: List<SetExercise> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutProgressViewHolder {
        return WorkoutProgressViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.card_exercise, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: WorkoutProgressViewHolder, position: Int) {
        presenter.onBindViewAtPosition(position, holder)
    }
}
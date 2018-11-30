package com.group15.fitnesstracker.dashboard.workout

import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.dashboard.workout.workoutProgress.WorkoutProgressFragment
import com.group15.fitnesstracker.db.Workout

class WorkoutItemViewHolder(workoutItemView: View) : RecyclerView.ViewHolder(workoutItemView), WorkoutContract.WorkoutItemView {
    private var workoutName: TextView = workoutItemView.findViewById(R.id.workoutName)
    private var workoutDescription: TextView = workoutItemView.findViewById(R.id.workoutDescription)
    private lateinit var workout: Workout
    private var fragmentManager: FragmentManager? = null

    init {
        workoutItemView.setOnClickListener {
            fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, WorkoutProgressFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }

    override fun setTitle(title: String) {
        workoutName.text = title
    }

    override fun setDescription(description: String) {
        workoutDescription.text = description
    }

    override fun setOnClickListener(fragmentManager: FragmentManager?, workout: Workout) {
        this.workout = workout
        this.fragmentManager = fragmentManager
    }
}
package com.awave.wrkout.dashboard.workout

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.awave.wrkout.R
import com.awave.wrkout.dashboard.workout.workoutProgress.WorkoutProgressActivity
import com.awave.wrkout.db.Workout
import com.awave.wrkout.util.Constants

class WorkoutItemViewHolder(workoutItemView: View) : RecyclerView.ViewHolder(workoutItemView), WorkoutContract.WorkoutItemView {
    private var workoutName: TextView = workoutItemView.findViewById(R.id.workoutName)
    private var workoutDescription: TextView = workoutItemView.findViewById(R.id.workoutDescription)
    private lateinit var workout: Workout
    private var fragmentManager: FragmentManager? = null

    init {
        workoutItemView.setOnClickListener {
            val intent = Intent(it.context, WorkoutProgressActivity::class.java).apply {
                putExtra(Constants.WORKOUT_ID, workout.workoutId)
            }
            it.context.startActivity(intent)
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
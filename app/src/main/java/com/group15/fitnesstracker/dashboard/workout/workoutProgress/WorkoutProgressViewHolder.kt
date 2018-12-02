package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.dashboard.workout.WorkoutItemViewHolder
import kotlinx.android.synthetic.main.card_exercise.view.*

class WorkoutProgressViewHolder(view: View): RecyclerView.ViewHolder(view), WorkoutProgressContract.ExerciseView {
    private val exerciseName: TextView = this.itemView.findViewById(R.id.exerciseName)
    private val setList: RecyclerView = this.itemView.findViewById(R.id.exerciseSetList)

    override fun showExerciseName(name: String) {
        exerciseName.text = name
    }

    override fun showSets(sets: Int) {
        val adapter: SetAdapter = SetAdapter()

        setList.layoutManager = LinearLayoutManager(this.itemView.context)
        setList.adapter = adapter

        adapter.sets = sets
    }
}

class SetAdapter: RecyclerView.Adapter<SetViewHolder>() {
    var sets: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        return SetViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_exercise_set, parent, false))
    }

    override fun getItemCount() = sets

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        //
    }

}

class SetViewHolder(view: View): RecyclerView.ViewHolder(view) {

}
package com.group15.fitnesstracker.dashboard.workout.createWorkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.base.BaseAdapter
import com.group15.fitnesstracker.db.Exercise

class CreateWorkoutAdapter(private val presenter: CreateWorkoutContract.Presenter): BaseAdapter<Exercise, CreateWorkoutAdapter.CreateWorkoutViewHolder>() {

    class CreateWorkoutViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateWorkoutViewHolder {
        return CreateWorkoutViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_exercise, parent, false))
    }

    override fun onBindViewHolder(holder: CreateWorkoutViewHolder, position: Int) {
        presenter.onBindViewAtPosition(position, holder)
    }
}
package com.group15.fitnesstracker.dashboard.workout.createWorkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.SetExercise
import kotlinx.android.synthetic.main.create_workout_activity.*

class CreateWorkoutActivity: AppCompatActivity(), CreateWorkoutContract.View {
    override lateinit var presenter: CreateWorkoutContract.Presenter
    private lateinit var adapter: CreateWorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_workout_activity)

        presenter = CreateWorkoutPresenter(this, this)
        adapter = CreateWorkoutAdapter(presenter)
        presenter.loadAllExercises()

        exerciseList.layoutManager = LinearLayoutManager(this)
        exerciseList.adapter = adapter
    }

    override fun showExercises(items: List<SetExercise>) {
        adapter.items = items
    }
}
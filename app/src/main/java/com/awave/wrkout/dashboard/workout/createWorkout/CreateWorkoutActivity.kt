package com.awave.wrkout.dashboard.workout.createWorkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.awave.wrkout.R
import com.awave.wrkout.db.SetExercise
import kotlinx.android.synthetic.main.activity_create_workout.*

class CreateWorkoutActivity: AppCompatActivity(), CreateWorkoutContract.View {
    override lateinit var presenter: CreateWorkoutContract.Presenter
    private lateinit var adapter: CreateWorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_workout)

        presenter = CreateWorkoutPresenter(this, this)
        adapter = CreateWorkoutAdapter(presenter)
        presenter.loadAllExercises()

        exerciseList.layoutManager = LinearLayoutManager(this)
        exerciseList.adapter = adapter

        createWorkout.setOnClickListener {
            val name = workoutName.text.toString()
            val description = workoutDescription.text.toString()

            presenter.createWorkout(name, description) { finish() }
        }
    }

    override fun showExercises(items: List<SetExercise>) {
        adapter.items = items
    }
}
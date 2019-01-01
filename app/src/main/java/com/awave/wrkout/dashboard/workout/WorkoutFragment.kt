package com.awave.wrkout.dashboard.workout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.awave.wrkout.R
import com.awave.wrkout.dashboard.workout.createWorkout.CreateWorkoutActivity
import com.awave.wrkout.db.Workout
import kotlinx.android.synthetic.main.fragment_workout_page.*

class WorkoutFragment: Fragment(), WorkoutContract.View {
    override lateinit var presenter: WorkoutContract.Presenter
    private lateinit var adapter: WorkoutAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = WorkoutPresenter(this, context, fragmentManager)
        return inflater.inflate(R.layout.fragment_workout_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadWorkouts()
        adapter = WorkoutAdapter(presenter)

        workoutList.layoutManager = LinearLayoutManager(context)
        workoutList.adapter = adapter

        createWorkout.setOnClickListener { startActivity(Intent(context, CreateWorkoutActivity::class.java)) }
    }

    override fun showWorkouts(workouts: List<Workout>) {
        adapter.items = workouts
    }

    override fun onResume() {
        super.onResume()
        if (::presenter.isInitialized) {
            presenter.loadWorkouts()
        }
    }
}

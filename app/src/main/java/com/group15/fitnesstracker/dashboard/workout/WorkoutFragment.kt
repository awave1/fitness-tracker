package com.group15.fitnesstracker.dashboard.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.dashboard.workout.exerciseList.ExerciseListFragment
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.Workout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_workout_page.*

class WorkoutFragment: Fragment(), WorkoutContract.View {
    override lateinit var presenter: WorkoutContract.Presenter
    private lateinit var adapter: WorkoutAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = WorkoutPresenter(this, context)
        return inflater.inflate(R.layout.fragment_workout_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadWorkouts()
        adapter = WorkoutAdapter(presenter)

        workoutList.layoutManager = LinearLayoutManager(context)
        workoutList.adapter = adapter
    }

    override fun showWorkouts(workouts: List<Workout>) {
        adapter.items = workouts
    }
}

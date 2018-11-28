package com.group15.fitnesstracker.dashboard.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.dashboard.workout.exerciseList.ExerciseListFragment
import kotlinx.android.synthetic.main.fragment_workout_page.*

class WorkoutFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_workout_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createWorkout.setOnClickListener {
            Toast.makeText(context, "create workout", Toast.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, ExerciseListFragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }

        pickWorkout.setOnClickListener {
            Toast.makeText(context, "pick workout", Toast.LENGTH_SHORT).show()
        }
    }
}

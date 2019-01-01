package com.awave.wrkout.dashboard.workout.exerciseList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.awave.wrkout.R
import kotlinx.android.synthetic.main.fragment_exercise_list.*

class ExerciseListFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exercise_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseList.setHasFixedSize(false)
        exerciseList.layoutManager = LinearLayoutManager(context)
        
    }
}
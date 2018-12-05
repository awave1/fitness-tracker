package com.group15.fitnesstracker.dashboard.workout.createWorkout

import android.annotation.SuppressLint
import android.content.Context
import android.widget.CheckBox
import android.widget.TextView
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.SetExercise
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateWorkoutPresenter(val view: CreateWorkoutContract.View, val context: Context): CreateWorkoutContract.Presenter {
    init {
        view.presenter = this
    }

    private var exercises = listOf<SetExercise>()
    private val selectedExercises = mutableListOf<SetExercise>()

    @SuppressLint("CheckResult")
    override fun loadAllExercises() {
        DbInjection.provideSetExerciseDao(context)
                .getAllExercises()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showExercises(it)
                    exercises = it
                }
    }

    override fun onBindViewAtPosition(position: Int, holder: CreateWorkoutAdapter.CreateWorkoutViewHolder) {
        val exercise = exercises[position]

        val name = holder.itemView.findViewById<TextView>(R.id.exerciseName)
        val description = holder.itemView.findViewById<TextView>(R.id.exerciseDescription)
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.isSelected)

        name.text = exercise.name
        description.text = exercise.description

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedExercises.add(exercise)
            } else {
                selectedExercises.remove(exercise)
            }
        }
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
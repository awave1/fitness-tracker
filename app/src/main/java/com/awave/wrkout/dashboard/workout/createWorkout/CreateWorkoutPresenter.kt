package com.awave.wrkout.dashboard.workout.createWorkout

import android.annotation.SuppressLint
import android.content.Context
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.awave.wrkout.R
import com.awave.wrkout.db.DbInjection
import com.awave.wrkout.db.SetExercise
import com.awave.wrkout.db.Workout
import com.awave.wrkout.db.WorkoutExercises
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
        val setNumber = holder.itemView.findViewById<EditText>(R.id.setNumber)
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.isSelected)

        name.text = exercise.name
        description.text = exercise.description

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (setNumber.text.toString().toInt() >= 1) {
                    exercise.numberOfSets = setNumber.text.toString().toInt()
                }
                selectedExercises.add(exercise)
            } else {
                selectedExercises.remove(exercise)
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun createWorkout(name: String, description: String, onComplete: () -> Unit) {
        DbInjection.provideWorkoutDao(context)
                .insert(Workout(name, description))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { id ->
                    val workoutExercises = selectedExercises.map {
                        WorkoutExercises(
                                workoutId = id.toInt(),
                                exerciseId = it.exerciseId,
                                numberOfSets = it.numberOfSets)
                    }

                    DbInjection.provideWorkoutExercisesDao(context)
                            .insertAll(workoutExercises)
                            .subscribeOn(Schedulers.io())
                            .subscribe { onComplete() }
                }
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
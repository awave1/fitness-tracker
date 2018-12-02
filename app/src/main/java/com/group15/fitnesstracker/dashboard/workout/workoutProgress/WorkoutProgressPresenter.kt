package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import android.app.AlertDialog
import android.content.Context
import com.group15.fitnesstracker.R
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.SetExercise
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class WorkoutProgressPresenter(private val view: WorkoutProgressContract.View, private val context: Context?): WorkoutProgressContract.Presenter {
    init {
        view.presenter = this
    }

    private var exercises = listOf<SetExercise>()
    private var workoutId: Int = 0

    override fun onBindViewAtPosition(position: Int, view: WorkoutProgressContract.ExerciseView) {
        val exercise = exercises[position]
        context?.let { ctx ->
            DbInjection.provideWorkoutExercisesDao(ctx)
                    .getSets(workoutId, exercise.exerciseId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        val sets = mutableListOf<SetExercise>()

                        repeat(it) { sets.add(exercise) }

                        view.showExerciseName(exercise.name)
                        view.showSets(sets)
                    }
        }
    }

    override fun loadExercises(workoutId: Int) {
        context?.let { ctx ->
            DbInjection.provideWorkoutExercisesDao(ctx)
                    .getExercises(workoutId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Timber.d("exercises ${it.size}")
                        view.showExercises(it)
                        exercises = it
                        this.workoutId = workoutId
                    }
        }
    }

    override fun showWarning() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage(R.string.workout_progress_warning)
                .setTitle(R.string.workout_progress_warning_title)
                .setPositiveButton(R.string.ok) { dialog, which ->
                    (context as WorkoutProgressActivity).finish()
                }
                .setNegativeButton(R.string.cancel) { dialog, which ->

                }
                .create()
                .show()
    }

    override fun finishWorkout(workoutId: Int) {

    }

    override fun start() {
    }

}
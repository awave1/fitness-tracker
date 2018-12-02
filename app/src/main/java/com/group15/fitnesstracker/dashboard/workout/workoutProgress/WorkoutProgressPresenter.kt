package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import android.content.Context
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

    override fun onBindViewAtPosition(position: Int, view: WorkoutProgressContract.ExerciseView) {
        val exercise = exercises[position]
        view.showExerciseName(exercise.name)
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
                    }
        }
    }

    override fun start() {
    }

}
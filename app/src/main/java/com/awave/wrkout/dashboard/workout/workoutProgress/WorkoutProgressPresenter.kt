package com.awave.wrkout.dashboard.workout.workoutProgress

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.awave.wrkout.R
import com.awave.wrkout.db.DbInjection
import com.awave.wrkout.db.History
import com.awave.wrkout.db.Set
import com.awave.wrkout.db.SetExercise
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class WorkoutProgressPresenter(private val view: WorkoutProgressContract.View, private val context: Context?): WorkoutProgressContract.Presenter {
    init {
        view.presenter = this
    }

    private var exercises = listOf<SetExercise>()
    private var sets = mutableListOf<Set>()
    private var workoutId: Int = 0

    override fun onBindViewAtPosition(position: Int, view: WorkoutProgressContract.ExerciseView) {
        val exercise = exercises[position]
        context?.let { ctx ->
            DbInjection.provideWorkoutExercisesDao(ctx)
                    .getSets(workoutId, exercise.exerciseId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Timber.d("no of sets: $it")
                        val sets = mutableListOf<Set>()

                        repeat(it) {
                            sets.add(Set(workoutId = workoutId, exerciseId = exercise.exerciseId))
                        }

                        Timber.d("exercise ${exercise.name} with ${sets.size} sets")

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

    override fun finishWorkout(userId: Int, workoutId: Int, completedSets: HashMap<Int, MutableList<Set>>, onComplete: () -> Unit) {
        context?.let { ctx ->
            val historyDao = DbInjection.provideHistoryDao(ctx)
            val setDao = DbInjection.provideSetDao(ctx)

            Timber.d("userId $userId, workoutId $workoutId")

            completedSets.keys.forEach {
                setDao.saveSets(completedSets[it]!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
            }

            historyDao.insertHistory(History(userId = userId, workoutId = workoutId))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(ctx, "Finished workout!", Toast.LENGTH_SHORT).show()
                        onComplete()
                    }, {
                        Toast.makeText(ctx, "Failed to save!", Toast.LENGTH_SHORT).show()
                        Timber.e(it, "failed to save history")
                    })
        }
    }

    override fun start() {
    }

}
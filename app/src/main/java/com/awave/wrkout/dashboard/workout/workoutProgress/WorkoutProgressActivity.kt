package com.awave.wrkout.dashboard.workout.workoutProgress

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.awave.wrkout.R
import com.awave.wrkout.db.Set
import com.awave.wrkout.db.SetExercise
import com.awave.wrkout.util.Constants
import kotlinx.android.synthetic.main.activity_workout_progress.*
import timber.log.Timber

class WorkoutProgressActivity: AppCompatActivity(), WorkoutProgressContract.View {
    override lateinit var presenter: WorkoutProgressContract.Presenter
    private lateinit var adapter: WorkoutProgressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_progress)


        val sharedPref = getSharedPreferences(
                resources?.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        )

        val userId = sharedPref?.getInt(Constants.CURRENT_USER_ID, -1) as Int
        val workoutId = intent.getIntExtra(Constants.WORKOUT_ID, 0)
        Timber.d("workout $workoutId")

        presenter = WorkoutProgressPresenter(this, this)
        presenter.loadExercises(workoutId)

        adapter = WorkoutProgressAdapter(presenter as WorkoutProgressPresenter)

        exerciseList.layoutManager = LinearLayoutManager(this)
        exerciseList.adapter = adapter

        finishWorkout.setOnClickListener {
            val isFinished = adapter.items.filterIndexed { index, _ ->
                val vh = exerciseList.findViewHolderForAdapterPosition(index) as WorkoutProgressViewHolder
                Timber.d("finished? ${vh.isComplete()}")
                return@filterIndexed vh.isComplete()
            }.size == adapter.items.size

            if (isFinished) {
                val completedSets = HashMap<Int, MutableList<Set>>()

                adapter.items.forEachIndexed { index, setExercise ->
                    val vh = exerciseList.findViewHolderForAdapterPosition(index) as WorkoutProgressViewHolder
                    completedSets[setExercise.exerciseId] = vh.getSets()
                }

                presenter.finishWorkout(userId, workoutId, completedSets) { finish() }
            } else {
                presenter.showWarning()
            }
        }
    }

    override fun showExercises(exercises: List<SetExercise>) {
        adapter.items = exercises
    }

    override fun onBackPressed() {
        presenter.showWarning()
    }
}
package com.awave.wrkout.dashboard.workout.workoutProgress

import com.awave.wrkout.base.BasePresenter
import com.awave.wrkout.base.BaseView
import com.awave.wrkout.db.Set
import com.awave.wrkout.db.SetExercise

interface WorkoutProgressContract {
    interface View: BaseView<Presenter> {
        fun showExercises(exercises: List<SetExercise>)
    }

    interface Presenter: BasePresenter {
        fun onBindViewAtPosition(position: Int, view: ExerciseView)
        fun loadExercises(workoutId: Int)
        fun showWarning()
        fun finishWorkout(userId: Int, workoutId: Int, completedSets: HashMap<Int, MutableList<Set>>, onComplete: () -> Unit)
    }

    interface ExerciseView {
        fun showExerciseName(name: String)
        fun showSets(sets: MutableList<Set>)
        fun getSets(): MutableList<Set>
    }
}
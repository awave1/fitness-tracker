package com.group15.fitnesstracker.dashboard.workout.workoutProgress

import com.group15.fitnesstracker.base.BasePresenter
import com.group15.fitnesstracker.base.BaseView
import com.group15.fitnesstracker.db.Set
import com.group15.fitnesstracker.db.Exercise

interface WorkoutProgressContract {
    interface View: BaseView<Presenter> {
        fun showExercises(exercises: List<Exercise>)
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
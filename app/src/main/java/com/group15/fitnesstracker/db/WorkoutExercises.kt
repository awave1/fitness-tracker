package com.group15.fitnesstracker.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity (
        primaryKeys = ["workoutId", "exerciseId"],
        foreignKeys = [
            ForeignKey(entity = Workout::class, parentColumns = ["workoutId"], childColumns = ["workoutId"]),
            ForeignKey(entity = SetExercise::class, parentColumns = ["exerciseId"], childColumns = ["exerciseId"])
        ]
)
data class WorkoutExercises (
        val workoutId: Int,
        val exerciseId: Int,
        @NonNull val reps: Int
)

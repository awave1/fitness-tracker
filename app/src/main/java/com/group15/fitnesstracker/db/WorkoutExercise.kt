package com.group15.fitnesstracker.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity (
        primaryKeys = ["workoutId", "exerciseId"],
        foreignKeys = [
            ForeignKey(entity = Workout::class, parentColumns = ["workoutId"], childColumns = ["workoutId"]),
            ForeignKey(entity = Exercise::class, parentColumns = ["exerciseId"], childColumns = ["exerciseId"])
        ]
)
data class WorkoutExercise (
        var workoutId: Int,
        var exerciseId: Int,
        @NonNull var numberOfSets: Int
)

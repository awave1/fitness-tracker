package com.awave.wrkout.db

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
        var workoutId: Int,
        var exerciseId: Int,
        @NonNull var numberOfSets: Int
)

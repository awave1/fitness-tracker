package com.awave.wrkout.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Workout::class, parentColumns = ["workoutId"], childColumns = ["workoutId"]),
                       ForeignKey(entity = SetExercise::class, parentColumns = ["exerciseId"], childColumns = ["exerciseId"])])
data class Set (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        var workoutId: Int = 0,
        var exerciseId: Int = 0,
        var reps: Int = -1,
        var weight: Double = 0.0,

        @Ignore var isComplete: Boolean = false
)
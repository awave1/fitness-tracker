package com.group15.fitnesstracker.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import java.util.*

@Entity(primaryKeys = ["workoutId", "trainerId", "userId"],
        foreignKeys = [ForeignKey(entity = Workout::class, parentColumns = ["workoutId"], childColumns = ["workoutId"]),
                       ForeignKey(entity = Trainer::class, parentColumns = ["id"], childColumns = ["trainerId"]),
                       ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"])])
data class ScheduleItem (
        @NonNull var workoutId: Int = 0,
        @NonNull var trainerId: Int = 0,
        @NonNull var userId: Int = 0,
        @NonNull var from: Date = Date(),
        @NonNull var to: Date = Date()
)

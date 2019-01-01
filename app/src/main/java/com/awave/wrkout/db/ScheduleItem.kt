package com.awave.wrkout.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(foreignKeys = [ForeignKey(entity = Workout::class, parentColumns = ["workoutId"], childColumns = ["workoutId"]),
                       ForeignKey(entity = Trainer::class, parentColumns = ["id"], childColumns = ["trainerId"]),
                       ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"])])
data class ScheduleItem (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @NonNull var workoutId: Int = 0,
        @NonNull var trainerId: Int = 0,
        @NonNull var userId: Int = 0,
        @NonNull var from: Date = Date(),
        @NonNull var to: Date = Date()
)

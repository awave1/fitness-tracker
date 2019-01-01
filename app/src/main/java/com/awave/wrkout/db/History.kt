package com.awave.wrkout.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"]),
                       ForeignKey(entity = Workout::class, parentColumns = ["workoutId"], childColumns = ["workoutId"])])
data class History (
    @PrimaryKey(autoGenerate = true) val recordingId: Int = 0,
    val userId: Int = 0,
    val workoutId: Int = 0
)



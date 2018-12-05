package com.group15.fitnesstracker.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"]),
                       ForeignKey(entity = Workout::class, parentColumns = ["workoutId"], childColumns = ["workoutId"])])
data class History (
    @PrimaryKey(autoGenerate = true) var recordingId: Int = 0,
    var userId: Int = 0,
    var workoutId: Int = 0
)



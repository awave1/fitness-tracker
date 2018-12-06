package com.group15.fitnesstracker.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["workoutId, trainerId, userId, recordingId"],
        foreignKeys = [ForeignKey(entity = Workout::class, parentColumns = ["workoutId"], childColumns = ["workoutId"]),
                       ForeignKey(entity = Trainer::class, parentColumns = ["trainerId"], childColumns = ["trainerId"]),
                       ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"]),
                       ForeignKey(entity = Records::class, parentColumns = ["recordId"], childColumns = ["recordingId"])])
data class Schedule (
    var workoutId: Int = 0,
    var trainerId: Int = 0,
    var userId: Int = 0,
    var recordingId: Int = 0
)

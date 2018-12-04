package com.group15.fitnesstracker.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["userId", "workoutId"],
        foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"]),
                       ForeignKey(entity = Workout::class, parentColumns = ["workoutId"], childColumns = ["workoutId"])])
data class History (
    var userId: Int = 0,
    var workoutId: Int = 0
)



package com.group15.fitnesstracker.db

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(primaryKeys = ["trainerid, userid"],
        foreignKeys = [
            ForeignKey(entity = Trainer::class, parentColumns = arrayOf("trainerId"), childColumns = arrayOf("trainerId")),
            ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("userId"))
        ])
data class Trains (
    var trainerId: Int,
    var userId: Int
)

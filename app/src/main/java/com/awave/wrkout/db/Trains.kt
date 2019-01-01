package com.awave.wrkout.db

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(primaryKeys = ["trainerId", "userId"],
        foreignKeys = [
            ForeignKey(entity = Trainer::class, parentColumns = ["id"], childColumns = ["trainerId"]),
            ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"])
        ])
data class Trains (
    var trainerId: Int,
    var userId: Int
)

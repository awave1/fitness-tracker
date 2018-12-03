package com.group15.fitnesstracker.db

import java.util.Date

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"])])
data class NutritionRecording (
        @PrimaryKey var recordingId: Int = 0,
        var userId: Int,
        var date: Date = Date(),
        var calories: Double?,
        var protein: Double?,
        var carbohydrate: Double?,
        var fat: Double?
)
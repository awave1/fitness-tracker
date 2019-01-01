package com.awave.wrkout.db

import java.util.Date

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"])])
data class NutritionRecording (
        @PrimaryKey(autoGenerate = true) var recordingId: Int = 0,
        var userId: Int = 0,
        var date: Date = Date(),
        var calories: Double? = 0.0,
        var protein: Double? = 0.0,
        var carbohydrate: Double? = 0.0,
        var fat: Double? = 0.0,

        @Ignore var micronutrients: List<MicronutrientRecording> = listOf()
)
package com.awave.wrkout.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(foreignKeys = [ForeignKey(entity = NutritionRecording::class, parentColumns = ["recordingId"], childColumns = ["recordingId"])])
data class MicronutrientRecording (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var recordingId: Int = 0,
    var name: String? = null,
    var amount: Double = 0.toDouble()
)

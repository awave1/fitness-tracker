package com.group15.fitnesstracker.db

import java.util.Date

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BodyMeasureRecording (
        @PrimaryKey(autoGenerate = true) val recordingId: Int = 0,
        var date: Date = Date(),
        var bodyFat: Double? = null,
        var weight: Double? = null
)

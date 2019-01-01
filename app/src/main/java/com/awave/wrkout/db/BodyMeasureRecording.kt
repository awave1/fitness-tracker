package com.awave.wrkout.db

import java.util.Date

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"])])
data class BodyMeasureRecording (
        @PrimaryKey(autoGenerate = true) val recordingId: Int = 0,
        val userId: Int?,
        var date: Date = Date(),
        var bodyFat: Double? = null,
        var weight: Double? = null
)

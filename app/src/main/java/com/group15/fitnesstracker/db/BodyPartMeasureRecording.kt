package com.group15.fitnesstracker.db

import java.util.Date

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = BodyMeasureRecording::class, parentColumns = ["recordingId"], childColumns = ["recordingId"]),
                       ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"])])
data class BodyPartMeasureRecording (
        @PrimaryKey(autoGenerate = true) var recordingId: Int = 0,
        val userId: Int?,
        var date: Date? = Date(),
        var bodyPart: String?,
        var bodyPartSize: Double?
)
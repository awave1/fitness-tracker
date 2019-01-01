package com.awave.wrkout.db;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"],childColumns = ["userId"])])
data class Goal (
    @PrimaryKey(autoGenerate = true) var goalId: Int = 0,
    val userId: Int?,
    var goalDescription: String? = null,
    var completionDate: Date? = Date()
)

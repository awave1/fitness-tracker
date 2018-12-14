package com.group15.fitnesstracker.db

import androidx.annotation.NonNull
import androidx.room.*

@Entity
data class Exercise (
    @PrimaryKey(autoGenerate = true) var exerciseId: Int = 0,
    @NonNull var name: String = "",
    var description: String = "",
    var time: Double = 0.0,

    @Ignore var isSelected: Boolean = false,
    @Ignore var numberOfSets: Int = 0
)

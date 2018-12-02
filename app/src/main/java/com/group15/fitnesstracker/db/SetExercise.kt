package com.group15.fitnesstracker.db

import androidx.annotation.NonNull
import androidx.room.*

@Entity
data class SetExercise (
    @PrimaryKey(autoGenerate = true) var exerciseId: Int = 0,
    @NonNull var name: String = "",
    var description: String = "",
    @Ignore var isSelected: Boolean = false
)

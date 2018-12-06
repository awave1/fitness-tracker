package com.group15.fitnesstracker.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trainer (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @NonNull var firstName: String,
        @NonNull var lastName: String,
        @NonNull var email: String
)


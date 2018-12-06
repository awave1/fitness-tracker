package com.group15.fitnesstracker.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trainer (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @NonNull var email: String,
        @NonNull var password: String,
        @NonNull var firstName: String,
        @NonNull var lastName: String
)


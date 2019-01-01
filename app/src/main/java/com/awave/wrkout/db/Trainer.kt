package com.awave.wrkout.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["email"], unique = true)])
data class Trainer (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @NonNull var email: String,
        @NonNull var password: String,
        @NonNull var firstName: String,
        @NonNull var lastName: String
)


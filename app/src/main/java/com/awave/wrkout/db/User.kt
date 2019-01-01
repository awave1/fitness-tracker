package com.awave.wrkout.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["username"], unique = true)])
data class User(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @NonNull val username: String,
        @NonNull val password: String,
        @NonNull val firstName: String,
        @NonNull val lastName: String,
        @NonNull val age: Int,
        @NonNull val weight: Double
)

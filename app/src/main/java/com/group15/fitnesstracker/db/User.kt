package com.group15.fitnesstracker.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = arrayOf("username"), unique = true)])
data class User(
        @PrimaryKey(autoGenerate = true) var id: Long = 0L,
        @NonNull val username: String,
        @NonNull val password: String,
        @NonNull val firstName: String,
        @NonNull val lastName: String,
        @NonNull val age: Int,
        @NonNull val weight: Double
)

package com.group15.fitnesstracker.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class SetExercise (
        @PrimaryKey(autoGenerate = true) val exerciseId: Int = 0,
        @NonNull val name: String = "",
        val description: String = ""
)

//
//class Set {
//    public int reps;
//    public double weight;
//}

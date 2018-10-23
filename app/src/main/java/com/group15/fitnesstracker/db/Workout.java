package com.group15.fitnesstracker.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Workout {
    @PrimaryKey
    public int workoutId;

    public String routineDescription;

    public String name;
}

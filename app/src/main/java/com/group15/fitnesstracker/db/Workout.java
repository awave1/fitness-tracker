package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Workout {
    public Workout(String name, String routineDescription) {
        this.workoutId = 1;
        this.name = name;
        this.routineDescription = routineDescription;
    }

    @PrimaryKey(autoGenerate = true)
    public int workoutId;

    public String routineDescription;

    public String name;
}

package com.awave.wrkout.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Workout {
    public Workout(String name, String routineDescription) {
        this.name = name;
        this.routineDescription = routineDescription;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int workoutId = 0;

    public String routineDescription;

    public String name;
}

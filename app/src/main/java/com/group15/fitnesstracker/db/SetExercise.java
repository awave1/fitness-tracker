package com.group15.fitnesstracker.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
                entity = Workout.class,
                parentColumns = "workoutId",
                childColumns = "workoutId")
)
public class SetExercise {
    public SetExercise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @PrimaryKey
    @NonNull
    public String name;

    public int workoutId;

    public String description;
// @TODO: cant save arrays
//    public Set[] sets;
}

class Set {
    public int reps;
    public double weight;
}

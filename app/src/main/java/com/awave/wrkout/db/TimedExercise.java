package com.awave.wrkout.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TimedExercise {
    @PrimaryKey
    @NonNull
    public String name;

//    @ForeignKey()
    public int workoutId;

    public String description;

    public double time;
}

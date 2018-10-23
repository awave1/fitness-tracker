package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TimedExercise {
    @PrimaryKey
    public String name;

//    @ForeignKey()
    public int workoutId;

    public String description;

    public double time;
}

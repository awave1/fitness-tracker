package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Timed_Exercise {
    @PrimaryKey
    public String name;

//    @ForeignKey()
    public int workoutId;

    public String description;

    public double time;
}

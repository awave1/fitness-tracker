package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Set_Exercise {
    @PrimaryKey
    public String name;

//    @ForeignKey()
    public int workout_id;

    public String description;

    public Set[] sets;
}
class Set {
    public int reps;
    public double weight;
}

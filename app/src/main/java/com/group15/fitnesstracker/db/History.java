package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class History {
    @PrimaryKey
    //TODO: @ForeignKey()
    public int userId;

    @PrimaryKey
    //TODO: @ForeignKey()
    public int workoutId;
}

package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Schedule {
    @PrimaryKey
//    @ForeignKey()
    public int workoutId;

    @PrimaryKey
//    @ForeignKey()
    public int trainerId;

    @PrimaryKey
//    @ForeignKey()
    public int userId;

    @PrimaryKey
//    @ForeignKey()
    public int recordingId;
}

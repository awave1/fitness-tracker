package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Schedule {
    @PrimaryKey
//    @ForeignKey()
    public int workoutId;

    @PrimaryKey
//    @ForeignKey()
    public int trainer_id;

    @PrimaryKey
//    @ForeignKey()
    public int user_id;

    @PrimaryKey
//    @ForeignKey()
    public int recording_id;
}

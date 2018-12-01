package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Workout.class,parentColumns = "workoutId",childColumns = "workoutId"),
@ForeignKey(entity = Trainer.class,parentColumns = "trainerId",childColumns = "trainerId"),
@ForeignKey(entity = User.class,parentColumns = "id",childColumns = "userId"),
@ForeignKey(entity = Records.class,parentColumns = "recordId",childColumns = "recordingId")})
public class Schedule {
    @PrimaryKey
    public int workoutId;

    @PrimaryKey
    public int trainerId;

    @PrimaryKey
    public int userId;

    @PrimaryKey
    public int recordingId;
}

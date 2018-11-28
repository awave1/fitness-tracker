package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = NutritionRecording.class,
                                  parentColumns = "recording_id",
                                  childColumns = " recording_id"))
public class MicronutrientRecording {
    @PrimaryKey
    public int recordingId;

    public String name;

    public double amount;
}

package com.group15.fitnesstracker.db;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BodyMeasureRecording {
    public BodyMeasureRecording (Date date, double bodyfat, double weight) {
        this.date = date;
        this.bodyfat = bodyfat;
        this.weight = weight;
    }

    @PrimaryKey(autoGenerate = true)
    public int recordingId = 0;

    public Date date;

    public double bodyfat;

    public double weight;
}

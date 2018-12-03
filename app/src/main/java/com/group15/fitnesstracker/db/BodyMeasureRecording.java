package com.group15.fitnesstracker.db;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BodyMeasureRecording {
    public BodyMeasureRecording (Date date, double bodyfat, double weight) {
        recordingId = 1;
        this.date = date;
        this.bodyfat = bodyfat;
        this.weight = weight;
    }

    @PrimaryKey
    public int recordingId;

    public Date date;

    public double bodyfat;

    public double weight;
}

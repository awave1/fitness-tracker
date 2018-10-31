package com.group15.fitnesstracker.db;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BodyMeasureRecording {
    @PrimaryKey
    public int recordingId;

    public Date date;

    public double bodyfat;

    public double weight;

    public BodyPartMeasurement[] bodyPartMeasurements;
}

class BodyPartMeasurement {
    public String bodypart;
    public double bodypartSize;
}

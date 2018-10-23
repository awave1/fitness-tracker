package com.group15.fitnesstracker.db;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BodyMeasure_Recording {
    @PrimaryKey
    public int recording_id;

    public Date date;

    public double bodyfat;

    public double weight;

    public BodyPartMeasurement[] bodyPartMeasurements;
}

class BodyPartMeasurement {
    public String Bodypart;
    public double Bodypart_size;
}
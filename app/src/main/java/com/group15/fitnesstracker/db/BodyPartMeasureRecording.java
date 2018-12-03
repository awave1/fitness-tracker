package com.group15.fitnesstracker.db;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = BodyMeasureRecording.class,parentColumns = "recordingId",childColumns = "recordingId"))
public class BodyPartMeasureRecording {
    @PrimaryKey
    public int recordingId;

    public Date date;

    public String bodypart;

    public double bodypartSize;
}
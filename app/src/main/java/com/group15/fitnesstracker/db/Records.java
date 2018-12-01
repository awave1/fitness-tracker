package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = User.class,parentColumns = "id",childColumns = "userId")
        ,@ForeignKey(entity = NutritionRecording.class,parentColumns = "recordingId",childColumns = "recordId")
        ,@ForeignKey(entity = BodyMeasureRecording.class,parentColumns = "recordingId",childColumns = "recordId")})
public class Records {
    @PrimaryKey
    public String userId;

    @PrimaryKey
//    @ForeignKey() right now link to both may have to change
    public String recordId;
}

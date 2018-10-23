package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


//TODO:implement foreign key
@Entity(foreignKeys = @ForeignKey(entity = Nutrition_Recording.class,
                                  parentColumns = "recording_id",
                                  childColumns = " recording_id"))
public class Micronutrient_Recording {
    @PrimaryKey
    public int recording_id;

    //TODO: create object for micronutrient (String name, double quantity)
}

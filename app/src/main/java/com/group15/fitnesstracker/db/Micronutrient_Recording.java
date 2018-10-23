package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = Nutrition_Recording.class,
                                  parentColumns = "recording_id",
                                  childColumns = " recording_id"))
public class Micronutrient_Recording {
    @PrimaryKey
    public int recording_id;

    public micronutrient[] micronutrients;
}
class micronutrient {
    public String name;
    public double amount;
}
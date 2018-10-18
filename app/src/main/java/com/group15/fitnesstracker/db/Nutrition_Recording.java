package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Nutrition_Recording {
    @PrimaryKey
    public int recording_id;

    //TODO: create date and time object?
    public String day;
    public String month;
    public String year;
    public String time;

    //TODO: create object for bodypart measurement
    public String Bodypart;
    public double Bodypart_size;

    //TODO:create object with macronutrients (protein carbohydrate fat)

}

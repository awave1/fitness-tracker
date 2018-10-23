package com.group15.fitnesstracker.db;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Nutrition_Recording {
    @PrimaryKey
    public int recording_id;

    public Date date;

    public double calories;

    public macronutrient macronutrient;
}

class macronutrient {
    public double protein;
    public double carbohydrate;
    public double fat;
}
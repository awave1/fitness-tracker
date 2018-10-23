package com.group15.fitnesstracker.db;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NutritionRecording {
    @PrimaryKey
    public int recordingId;

    public Date date;

    public double calories;

    public macronutrient macronutrient;
}

class Macronutrient {
    public double protein;
    public double carbohydrate;
    public double fat;
}

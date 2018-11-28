package com.group15.fitnesstracker.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout")
public class Workout {
    public Workout(String name, String routineDescription) {
        this.workoutId = 1;
        this.name = name;
        this.routineDescription = routineDescription;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workout_id")
    public int workoutId;

    @ColumnInfo(name = "routine_description")
    public String routineDescription;

    @ColumnInfo(name = "name")
    public String name;
}

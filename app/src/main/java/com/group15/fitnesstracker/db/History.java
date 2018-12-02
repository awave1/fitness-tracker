package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"userId, workoutId"},foreignKeys = {@ForeignKey(entity = User.class,parentColumns = "id",childColumns = "userId"),
                        @ForeignKey(entity = Workout.class,parentColumns = "workoutId",childColumns = "workoutId")
})
public class History {
    public int userId;

    public int workoutId;
}

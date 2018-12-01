package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {@ForeignKey(entity = Trainer.class,parentColumns = "trainerId",childColumns = "trainerId"),
@ForeignKey(entity = User.class,parentColumns = "id",childColumns = "userId")})
public class Trains {
    public int trainerId;
    public int userId;
}

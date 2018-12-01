package com.group15.fitnesstracker.db;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class,parentColumns = "id",childColumns = "id"))
public class Goal {
    @PrimaryKey
    public int id;

    public String goalDescription;

    public Date completionDate;
}

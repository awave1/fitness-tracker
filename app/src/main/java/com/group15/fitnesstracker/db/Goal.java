package com.group15.fitnesstracker.db;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Goal {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    public String goalDescription;

    public Date completionDate;
}

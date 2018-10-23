package com.group15.fitnesstracker.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class History {
    @PrimaryKey
    //TODO: @ForeignKey()
    public int user_id;

    @PrimaryKey
    //TODO: @ForeignKey()
    public int workout_id;
}

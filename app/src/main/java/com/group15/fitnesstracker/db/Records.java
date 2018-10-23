package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Records {
    @PrimaryKey
//    @ForeignKey()
    public String userId;

    @PrimaryKey
//    @ForeignKey()
    public String recordId;
}

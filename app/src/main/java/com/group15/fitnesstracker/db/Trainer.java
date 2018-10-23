package com.group15.fitnesstracker.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Trainer {
    @PrimaryKey
    public String trainer_id;

    public String firstName;

    public String lastName;

    public String email;
}
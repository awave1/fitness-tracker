package com.group15.fitnesstracker.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = {"username"}, unique = true)})
public class User {
    public User(String username, String password, String firstName, String lastName, int age, double weight) {
        this.id = 1; // required for autoincrement
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.weight = weight;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "username")
    public String username;

    @NonNull
    @ColumnInfo(name = "password")
    public String password; // NOT plaintext fyi

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "weight")
    public double weight;
}

package com.group15.fitnesstracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.group15.fitnesstracker.db.dao.UserDao

const val DB_NAME = "fitness_tracker.db"

@Database(entities = [User::class], version = 1)
abstract class FitnessTrackerDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var db: FitnessTrackerDatabase? = null

        fun instance(context: Context): FitnessTrackerDatabase? {
            if (db == null) {
                db = Room.databaseBuilder(context, FitnessTrackerDatabase::class.java, DB_NAME).build()
            }

            return db
        }
    }
}
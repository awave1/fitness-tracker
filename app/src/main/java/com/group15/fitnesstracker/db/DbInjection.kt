package com.group15.fitnesstracker.db

import android.content.Context
import com.group15.fitnesstracker.db.dao.UserDao
import com.group15.fitnesstracker.db.dao.WorkoutDao

object DbInjection {
    fun provideDb(context: Context): FitnessTrackerDatabase {
        return FitnessTrackerDatabase.instance(context)
    }

    fun provideUserDao(context: Context): UserDao = provideDb(context).userDao()

    fun provideWorkoutDao(context: Context): WorkoutDao = provideDb(context).workoutDao()
}
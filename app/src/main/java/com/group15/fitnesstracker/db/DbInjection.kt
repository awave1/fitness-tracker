package com.group15.fitnesstracker.db

import android.content.Context

object DbInjection {
    fun provideDb(context: Context) = FitnessTrackerDatabase.instance(context)

    fun provideUserDao(context: Context) = provideDb(context).userDao()

    fun provideWorkoutDao(context: Context) = provideDb(context).workoutDao()
}
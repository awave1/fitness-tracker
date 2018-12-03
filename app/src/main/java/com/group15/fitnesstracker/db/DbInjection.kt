package com.group15.fitnesstracker.db

import android.content.Context

object DbInjection {
    fun provideDb(context: Context) = FitnessTrackerDatabase.instance(context)

    fun provideUserDao(context: Context) = provideDb(context).userDao()

    fun provideWorkoutDao(context: Context) = provideDb(context).workoutDao()

    fun provideWorkoutExercisesDao(context: Context) = provideDb(context).workoutExercisesDao()

    fun provideBodyRecordingDao(context: Context) = provideDb(context).bodyRecordingDao()

    fun provideNutritionRecordingDao(context: Context) = provideDb(context).nutritionRecordingDao()
}
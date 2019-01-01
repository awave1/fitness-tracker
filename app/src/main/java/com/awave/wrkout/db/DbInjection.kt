package com.awave.wrkout.db

import android.content.Context

object DbInjection {
    fun provideDb(context: Context) = FitnessTrackerDatabase.instance(context)

    fun provideUserDao(context: Context) = provideDb(context).userDao()

    fun provideWorkoutDao(context: Context) = provideDb(context).workoutDao()

    fun provideWorkoutExercisesDao(context: Context) = provideDb(context).workoutExercisesDao()

    fun provideGoalDao(context: Context) = provideDb(context).goalDao()

    fun provideBodyRecordingDao(context: Context) = provideDb(context).bodyRecordingDao()

    fun provideNutritionRecordingDao(context: Context) = provideDb(context).nutritionRecordingDao()

    fun provideBodyPartRecordingDao(context: Context) = provideDb(context).bodyPartRecordingDao()

    fun provideHistoryDao(context: Context) = provideDb(context).historyDao()

    fun provideSetDao(context: Context) = provideDb(context).setDao()

    fun provideSetExerciseDao(context: Context) = provideDb(context).setExerciseDao()

    fun provideTrainerDao(context: Context) = provideDb(context).trainerDao()

    fun probideTrainsDao(context: Context) = provideDb(context).trainsDao()

    fun provideScheduleItemDao(context: Context) = provideDb(context).scheduleItemDao()

    fun provideMicronutrientDao(context: Context) = provideDb(context).micronutrientDao()
}
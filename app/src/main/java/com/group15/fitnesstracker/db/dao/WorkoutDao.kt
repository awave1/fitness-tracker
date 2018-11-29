package com.group15.fitnesstracker.db.dao

import androidx.room.*
import com.group15.fitnesstracker.db.Workout
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workout: Workout): Completable

    @Query("select * from Workout")
    fun getAll(): Maybe<List<Workout>>

    @Query("select * from Workout where workoutId = :id")
    fun getWorkout(id: Int): Maybe<Workout>

    @Delete()
    fun deleteWorkout(workout: Workout): Completable
}
package com.group15.fitnesstracker.db.dao

import androidx.room.*
import com.group15.fitnesstracker.db.Workout
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workout: Workout): Completable

    @Query("select * from workout")
    fun getAll(): Maybe<List<Workout>>

    @Query("select * from workout where workout_id = :id")
    fun getWorkout(id: Int): Maybe<Workout>

    @Delete()
    fun deleteWorkout(workout: Workout): Completable
}
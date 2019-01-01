package com.awave.wrkout.db.dao

import androidx.room.*
import com.awave.wrkout.db.Workout
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface WorkoutDao {
    @Insert
    fun insert(workout: Workout): Maybe<Long>

    @Insert()
    fun insertAll(vararg workouts: Workout): Completable

    @Insert()
    fun insertAll_test(vararg workouts: Workout)

    @Query("select * from Workout")
    fun getAll(): Maybe<List<Workout>>

    @Query("select * from Workout where workoutId = :id")
    fun getWorkout(id: Int): Maybe<Workout>

    @Delete()
    fun deleteWorkout(workout: Workout): Completable
}
package com.group15.fitnesstracker.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.group15.fitnesstracker.db.Workout
import io.reactivex.Maybe

@Dao
interface WorkoutDao {
    @Query("select * from Workout")
    fun getAll(): Maybe<List<Workout>>
}
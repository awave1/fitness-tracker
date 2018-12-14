package com.group15.fitnesstracker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.group15.fitnesstracker.db.Exercise
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface ExerciseDao {
    @Insert
    fun insertAll(vararg exercise: Exercise): Completable

    @Insert
    fun insertAll_test(vararg exercise: Exercise)

    @Query("select * from Exercise")
    fun getAllExercises(): Maybe<List<Exercise>>
}

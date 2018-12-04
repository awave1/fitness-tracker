package com.group15.fitnesstracker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.group15.fitnesstracker.db.SetExercise
import io.reactivex.Completable

@Dao
interface SetExerciseDao {
    @Insert
    fun insertAll(vararg exercise: SetExercise): Completable

    @Insert
    fun insertAll_test(vararg exercise: SetExercise)
}

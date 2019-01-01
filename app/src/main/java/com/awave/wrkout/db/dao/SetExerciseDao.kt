package com.awave.wrkout.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.awave.wrkout.db.SetExercise
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface SetExerciseDao {
    @Insert
    fun insertAll(vararg exercise: SetExercise): Completable

    @Insert
    fun insertAll_test(vararg exercise: SetExercise)

    @Query("select * from SetExercise")
    fun getAllExercises(): Maybe<List<SetExercise>>
}

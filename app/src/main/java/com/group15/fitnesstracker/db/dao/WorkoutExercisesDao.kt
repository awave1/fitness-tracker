package com.group15.fitnesstracker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.group15.fitnesstracker.db.Exercise
import com.group15.fitnesstracker.db.WorkoutExercise
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface WorkoutExercisesDao {
    @Query("select * from Exercise as e join WorkoutExercise as w on e.exerciseId = w.exerciseId where w.workoutId = :workoutId")
    fun getExercises(workoutId: Int): Maybe<List<Exercise>>

    @Insert
    fun insert(workoutExercise: WorkoutExercise): Completable

    @Insert
    fun insertAll(vararg workoutExercises: WorkoutExercise): Completable

    @Insert
    fun insertAll(workoutExercises: List<WorkoutExercise>): Completable

    @Insert
    fun insertAll_test(vararg workoutExercises: WorkoutExercise)

    @Query("select numberOfSets from WorkoutExercise where workoutId = :workoutId and exerciseId = :exerciseId")
    fun getSets(workoutId: Int, exerciseId: Int): Observable<Int>
}
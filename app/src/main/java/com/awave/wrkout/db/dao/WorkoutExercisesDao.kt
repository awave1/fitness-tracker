package com.awave.wrkout.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.awave.wrkout.db.SetExercise
import com.awave.wrkout.db.WorkoutExercises
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface WorkoutExercisesDao {
    @Query("select * from SetExercise as e join WorkoutExercises as w on e.exerciseId = w.exerciseId where w.workoutId = :workoutId")
    fun getExercises(workoutId: Int): Maybe<List<SetExercise>>

    @Insert
    fun insert(workoutExercises: WorkoutExercises): Completable

    @Insert
    fun insertAll(vararg workoutExercises: WorkoutExercises): Completable

    @Insert
    fun insertAll(workoutExercises: List<WorkoutExercises>): Completable

    @Insert
    fun insertAll_test(vararg workoutExercises: WorkoutExercises)

    @Query("select numberOfSets from WorkoutExercises where workoutId = :workoutId and exerciseId = :exerciseId")
    fun getSets(workoutId: Int, exerciseId: Int): Observable<Int>
}
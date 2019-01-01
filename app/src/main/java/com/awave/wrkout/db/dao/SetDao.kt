package com.awave.wrkout.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.awave.wrkout.db.Set
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface SetDao {
    @Insert
    fun saveSet(set: Set): Completable

    @Insert
    fun saveSets(sets: MutableList<Set>): Completable

    @Query("""
        select s.workoutId, s.exerciseId, s.id, s.reps, s.weight
        from `Set` as s
        join History as h
            on h.workoutId = :workoutId and h.recordingId = :recordingId and h.userId = :userId
        where s.exerciseId = :exerciseId
    """)
    fun getCompletedSets(recordingId: Int, workoutId: Int, exerciseId: Int, userId: Int): Maybe<List<Set>>
}
package com.group15.fitnesstracker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.group15.fitnesstracker.db.Set

@Dao
interface SetDao {
    @Insert
    fun saveSet(set: Set)

    @Query("select * from `Set` join History as h on h.workoutId = :workoutId and h.recordingId = :recordingId and h.userId = :userId")
    fun getCompletedSets(recordingId: Int, workoutId: Int, exerciseId: Int, userId: Int)
}
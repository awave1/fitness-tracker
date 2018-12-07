package com.group15.fitnesstracker.db.dao

import com.group15.fitnesstracker.db.NutritionRecording

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface NutritionRecordingDao {

    @Insert
    fun insertNutritionRecordings(vararg nutritionRecordings: NutritionRecording): Completable

    @Insert
    fun createNutritionRecording(nutritionRecording: NutritionRecording): Maybe<Long>

    @Update
    fun updateNutritionRecordings(vararg nutritionRecordings: NutritionRecording): Completable

    @Delete
    fun deleteNutritionRecordings(vararg nutritionRecordings: NutritionRecording): Completable

    @Query("SELECT * FROM NutritionRecording")
    fun loadAllNutritionRecordings(): Maybe<List<NutritionRecording>>

    @Query("select * from NutritionRecording where userId = :userId")
    fun getNutritionRecordingsForUser(userId: Int): Maybe<List<NutritionRecording>>
}

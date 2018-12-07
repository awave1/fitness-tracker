package com.group15.fitnesstracker.db.dao

import androidx.room.*
import com.group15.fitnesstracker.db.MicronutrientRecording

import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface MicronutrientRecordingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createMicronutrientRecordings(micronutrientList: List<MicronutrientRecording>): Completable

    @Update
    fun updateMicronutrientRecordings(vararg micronutrientRecordings: MicronutrientRecording): Completable

    @Delete
    fun deleteMicronutrientRecordings(vararg micronutrientRecordings: MicronutrientRecording): Completable

    @Query("SELECT * FROM MicronutrientRecording")
    fun loadAllMicronutrientRecordings(): Maybe<List<MicronutrientRecording>>
}

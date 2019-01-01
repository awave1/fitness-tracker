package com.awave.wrkout.db.dao

import androidx.room.*
import com.awave.wrkout.db.MicronutrientRecording

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

    @Query("select * from MicronutrientRecording where recordingId = :recordingId")
    fun getMicronutrientsForRecording(recordingId: Int): Maybe<List<MicronutrientRecording>>
}

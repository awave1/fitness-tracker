package com.group15.fitnesstracker.db.dao;

import com.group15.fitnesstracker.db.MicronutrientRecording;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface MicronutrientRecordingDao {
    //TODO: yeah i like long classes names
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertMicronutrientRecordings(MicronutrientRecording... micronutrientRecordings);

    @Update
    public Completable updateMicronutrientRecordings(MicronutrientRecording... micronutrientRecordings);

    @Delete
    public Completable deleteMicronutrientRecordings(MicronutrientRecording... micronutrientRecordings);

    @Query("SELECT * FROM MicronutrientRecording")
    public Maybe<List<MicronutrientRecording>> loadAllMicronutrientRecordings();
}

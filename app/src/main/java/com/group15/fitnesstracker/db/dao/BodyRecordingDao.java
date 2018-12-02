package com.group15.fitnesstracker.db.dao;

import com.group15.fitnesstracker.db.BodyMeasureRecording;
import com.group15.fitnesstracker.db.Goal;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface BodyRecordingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertBodyRecordings(BodyMeasureRecording... bodyMeasureRecordings);

    @Update
    public Completable updateBodyRecordings(BodyMeasureRecording... bodyMeasureRecordings);

    @Delete
    public Completable deleteBodyRecordings(BodyMeasureRecording... bodyMeasureRecordings);

    @Query("SELECT * FROM BodyMeasureRecording")
    public Maybe<List<BodyMeasureRecording>> loadAllBodyRecordings();
}

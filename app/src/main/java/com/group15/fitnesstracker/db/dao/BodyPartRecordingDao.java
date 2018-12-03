package com.group15.fitnesstracker.db.dao;

import com.group15.fitnesstracker.db.BodyPartMeasureRecording;
import com.group15.fitnesstracker.db.BodyPartMeasureRecording;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface BodyPartRecordingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertBodyPartRecordings(BodyPartMeasureRecording... bodyPartMeasureRecordingsMeasureRecordings);

    @Update
    public Completable updateBodyPartRecordings(BodyPartMeasureRecording... bodyPartMeasureRecordings);

    @Delete
    public Completable deleteBodyPartRecordings(BodyPartMeasureRecording... bodyPartMeasureRecordings);

    @Query("SELECT * FROM BodyPartMeasureRecording")
    public Maybe<List<BodyPartMeasureRecording>> loadAllBodyPartRecordings();

}

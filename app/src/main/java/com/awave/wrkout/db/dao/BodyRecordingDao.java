package com.awave.wrkout.db.dao;

import com.awave.wrkout.db.BodyMeasureRecording;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Maybe;

@Dao
public interface BodyRecordingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertBodyRecordings(BodyMeasureRecording... bodyMeasureRecordings);

    @Update
    public Completable updateBodyRecordings(BodyMeasureRecording... bodyMeasureRecordings);

    @Delete
    public Completable deleteBodyRecordings(BodyMeasureRecording... bodyMeasureRecordings);

    @Query("SELECT * FROM BodyMeasureRecording")
    public Maybe<List<BodyMeasureRecording>> loadAllBodyRecordings();

    @Query("select * from BodyMeasureRecording where userId = :userId")
    public Maybe<List<BodyMeasureRecording>> loadUserBodyRecordings(int userId);
}

package com.awave.wrkout.db.dao;

import com.awave.wrkout.db.BodyPartMeasureRecording;

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
public interface BodyPartRecordingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertBodyPartRecordings(BodyPartMeasureRecording... bodyPartMeasureRecordingsMeasureRecordings);

    @Update
    public Completable updateBodyPartRecordings(BodyPartMeasureRecording... bodyPartMeasureRecordings);

    @Delete
    public Completable deleteBodyPartRecordings(BodyPartMeasureRecording... bodyPartMeasureRecordings);

    @Query("SELECT * FROM BodyPartMeasureRecording")
    public Maybe<List<BodyPartMeasureRecording>> loadAllBodyPartRecordings();

    @Query("select * from BodyPartMeasureRecording where userId = :userId")
    public Maybe<List<BodyPartMeasureRecording>> getRecordingsForUser(int userId);

}

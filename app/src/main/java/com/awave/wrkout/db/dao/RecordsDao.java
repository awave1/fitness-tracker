package com.awave.wrkout.db.dao;

import com.awave.wrkout.db.Records;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface RecordsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertRecords(Records... records);

    @Update
    public Completable updateRecords(Records... records);

    @Delete
    public Completable deleteRecords(Records... records);

    @Query("SELECT * FROM Records")
    public Maybe<List<Records>> loadAllRecords();
}

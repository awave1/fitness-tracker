package com.group15.fitnesstracker.db.dao;

import com.group15.fitnesstracker.db.Trains;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface TrainsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertTrains(Trains... trains);

    @Update
    public Completable updateTrains(Trains... trains);

    @Delete
    public Completable deleteTrains(Trains... trains);

    @Query("SELECT * FROM Trains")
    public Maybe<List<Trains>> loadAllTrains();
}

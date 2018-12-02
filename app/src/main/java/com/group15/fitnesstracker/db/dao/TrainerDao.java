package com.group15.fitnesstracker.db.dao;

import com.group15.fitnesstracker.db.Trainer;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface TrainerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertTrainers(Trainer... Trainers);

    @Update
    public Completable updateTrainers(Trainer... Trainers);

    @Delete
    public Completable deleteTrainers(Trainer... Trainers);

    @Query("SELECT * FROM Trainer")
    public Maybe<List<Trainer>> loadAllTrainers();
}

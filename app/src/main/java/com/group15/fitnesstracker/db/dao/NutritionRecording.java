package com.group15.fitnesstracker.db.dao;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Maybe;

public interface NutritionRecording {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertNutritionRecordings(NutritionRecording... nutritionRecordings);

    @Update
    public Completable updateNutritionRecordings(NutritionRecording... nutritionRecordings);

    @Delete
    public Completable deleteNutritionRecordings(NutritionRecording... nutritionRecordings);

    @Query("SELECT * FROM NutritionRecording")
    public Maybe<List<NutritionRecording>> loadAllNutritionRecordings();
}

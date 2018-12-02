package com.group15.fitnesstracker.db.dao;

import com.group15.fitnesstracker.db.Goal;

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
public interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertGoals(Goal... goals);

    @Update
    public Completable updateGoals(Goal... goals);

    @Delete
    public Completable deleteGoals(Goal... goals);

    @Query("SELECT * FROM Goal")
    public  Maybe<List<Goal>> loadAllGoals();
}

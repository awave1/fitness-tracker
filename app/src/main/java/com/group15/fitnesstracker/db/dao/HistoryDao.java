package com.group15.fitnesstracker.db.dao;

import com.group15.fitnesstracker.db.History;
import com.group15.fitnesstracker.db.Workout;

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
public interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertHistories(History... histories);

    @Update
    public Completable updateHistories(History... histories);

    @Delete
    public Completable deleteHistories(History... histories);

    @Query("SELECT * FROM History")
    public Maybe<List<History>> loadAllHistories();

//    @Query("select * from History where userId = :userId")
//    public Maybe<List<History>> getHistoryForUser(int userId);

    @Query("select * from Workout as w join History as h on h.workoutId = w.workoutId and h.userId = :userId")
    public Maybe<List<Workout>> getHistoryForUser(int userId);
}

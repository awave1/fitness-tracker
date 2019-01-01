package com.awave.wrkout.db.dao;

import com.awave.wrkout.db.Goal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Maybe;

@Dao
public interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGoal(goal: Goal): Completable

    @Insert()
    fun insertAll(vararg goals: Goal): Completable

    @Insert()
    fun insertAll_test(vararg goals: Goal)

    @Query("select * from Goal")
    fun getAll(): Maybe<List<Goal>>

    @Query("select * from Goal where userId = :userId")
    fun getGoalsForUser(userId: Int): Maybe<List<Goal>>

    @Delete()
    fun deleteGoal(goal: Goal): Completable
}

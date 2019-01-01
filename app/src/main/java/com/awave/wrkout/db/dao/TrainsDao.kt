package com.awave.wrkout.db.dao

import androidx.room.*
import com.awave.wrkout.db.Trains

import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface TrainsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrains(vararg trains: Trains): Completable

    @Insert
    fun addUsersToTrain(vararg train: Trains): Completable

    @Update
    fun updateTrains(vararg trains: Trains): Completable

    @Delete
    fun deleteTrains(vararg trains: Trains): Completable

    @Query("SELECT * FROM Trains")
    fun loadAllTrains(): Maybe<List<Trains>>
}

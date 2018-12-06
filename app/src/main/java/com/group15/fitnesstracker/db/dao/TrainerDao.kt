package com.group15.fitnesstracker.db.dao

import androidx.room.*
import com.group15.fitnesstracker.db.Trainer

import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface TrainerDao {
    @Insert
    fun createTrainer(trainer: Trainer): Maybe<Long>

    @Update
    fun updateTrainers(vararg Trainers: Trainer): Completable

    @Delete
    fun deleteTrainers(vararg Trainers: Trainer): Completable

    @Query("SELECT * FROM Trainer")
    fun loadAllTrainers(): Maybe<List<Trainer>>

    @Query("select * from Trainer where id = :id")
    fun getTrainer(id: Int): Maybe<Trainer>
}

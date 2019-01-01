package com.awave.wrkout.db.dao

import androidx.room.*
import com.awave.wrkout.db.Trainer

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

    @Query("select * from Trainer where email = :email and password = :pass")
    fun getTrainer(email: String, pass: String): Maybe<Trainer>
}

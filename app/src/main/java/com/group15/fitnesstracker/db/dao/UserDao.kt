package com.group15.fitnesstracker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.group15.fitnesstracker.db.User
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Completable

    @Query("select * from User")
    fun getAll(): Maybe<List<User>>

    @Query("select * from User where first_name = :firstName")
    fun getByName(firstName: String): Maybe<User>

    @Query("select * from User where id = :id")
    fun getById(id: Int): Maybe<User>

    @Query("select * from User where username = :username and password = :password")
    fun getByUsername(username: String, password: String): Maybe<User>
}
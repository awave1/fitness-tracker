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

    @Query("select * from users")
    fun getAll(): Maybe<List<User>>

    @Query("select * from users where first_name = :firstName")
    fun getByName(firstName: String): Maybe<User>

    @Query("select * from users where id = :id")
    fun getById(id: Int): Maybe<User>

    @Query("select * from users where username = :username")
    fun getByUsername(username: String): Maybe<User>
}
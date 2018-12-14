package com.group15.fitnesstracker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.group15.fitnesstracker.db.Schedule
import io.reactivex.Completable
import io.reactivex.Maybe
import java.util.*

@Dao
interface ScheduleDao {
    @Insert
    fun createScheduleItem(schedule: Schedule): Completable

    @Insert
    fun createItems(vararg schedule: Schedule): Completable

    @Query("select * from Schedule where trainerId = :id")
    fun getScheduleItemsForTrainer(id: Int): Maybe<MutableList<Schedule>>

    @Query("select * from Schedule where trainerId = :id and `from` = :from and `to` = :to")
    fun getScheduleItemsInRange(id: Int, from: Date, to: Date): Maybe<MutableList<Schedule>>
}
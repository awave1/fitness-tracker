package com.group15.fitnesstracker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.group15.fitnesstracker.db.ScheduleItem
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface ScheduleItemDao {
    @Insert
    fun createScheduleItem(scheduleItem: ScheduleItem)

    @Insert
    fun createItems(vararg scheduleItem: ScheduleItem): Completable

    @Query("select * from ScheduleItem where trainerId = :id")
    fun getScheduleItemsForTrainer(id: Int): Maybe<ScheduleItem>
}
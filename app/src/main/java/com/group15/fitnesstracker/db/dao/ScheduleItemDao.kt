package com.group15.fitnesstracker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.group15.fitnesstracker.db.ScheduleItem

@Dao
interface ScheduleItemDao {
    @Insert
    fun createScheduleItem(scheduleItem: ScheduleItem)

    @Insert
    fun createItems(vararg scheduleItem: ScheduleItem)
}
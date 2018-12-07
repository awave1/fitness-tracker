package com.group15.fitnesstracker.db.dao

import com.group15.fitnesstracker.db.NutritionRecording

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface NutritionRecordingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNutritionRecordings(vararg nutritionRecordings: NutritionRecording): Completable

    @Update
    fun updateNutritionRecordings(vararg nutritionRecordings: NutritionRecording): Completable

    @Delete
    fun deleteNutritionRecordings(vararg nutritionRecordings: NutritionRecording): Completable

    @Query("SELECT * FROM NutritionRecording")
    fun loadAllNutritionRecordings(): Maybe<List<NutritionRecording>>

    @Query("select * from NutritionRecording where userId = :userId")
    fun getNutritionRecordingsForUser(userId: Int): Maybe<List<NutritionRecording>>

    @Query("""
        select SUM(calories) as sumCalories, SUM(protein) as sumProtein,
               SUM(carbohydrate) as sumCarbs, SUM(fat) as sumFat
        from NutritionRecording where date == DATE('now', '-1 day') and userId = :userId
    """)
    fun getDailyStats(userId: Int): Maybe<UserStats>
}

data class UserStats (
        var sumCalories: Double,
        var sumProtein: Double,
        var sumCarbs: Double,
        var sumFat: Double
)
package com.awave.wrkout

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.awave.wrkout.db.*
import com.awave.wrkout.db.dao.WorkoutExercisesDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class HistoryTest {
    private lateinit var workoutExercisesDao: WorkoutExercisesDao
    private lateinit var db: FitnessTrackerDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, FitnessTrackerDatabase::class.java).build()
        workoutExercisesDao = db.workoutExercisesDao()

        db.workoutDao()
                .insertAll_test(
                        Workout("Strong 5x5 A", "Very"),
                        Workout("Strong 5x5 B", "Strong"))

        val user1 = User(
                username = "username1",
                password = "password",
                firstName = "first",
                lastName = "last",
                age = 99,
                weight = 99.9
        )

        db.userDao().insertAll(user1)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun createHistory_shouldInsertSuccessfully() {
        db.historyDao()
                .insertHistory(History(1, 1))
                .test()
                .assertValue {
                    return@assertValue it != (0).toLong()
                }
    }

    @Test
    fun createAndDeleteHistory_shouldCreateAndDeleteSuccessfully() {
        val history = History(1, 1)

        db.historyDao()
                .insertHistory(history)
                .test()
                .assertValue {
                    return@assertValue it != (0).toLong()
                }

        db.historyDao()
                .deleteHistories(history)
                .test()

        db.historyDao()
                .getHistoryForUser(1)
                .test()
                .assertValue {
                    return@assertValue it.isEmpty()
                }
    }
}
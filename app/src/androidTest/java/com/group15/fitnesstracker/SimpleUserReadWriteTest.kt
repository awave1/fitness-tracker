package com.group15.fitnesstracker

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.group15.fitnesstracker.db.FitnessTrackerDatabase
import com.group15.fitnesstracker.db.Schedule
import com.group15.fitnesstracker.db.User
import com.group15.fitnesstracker.db.dao.UserDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import org.junit.Rule



/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SimpleUserReadWriteTest {
    private lateinit var userDao: UserDao
    private lateinit var db: FitnessTrackerDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, FitnessTrackerDatabase::class.java).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun createMultipleUsers_shouldInsertAllSuccessfully() {
        val user1 = User(
                username = "username1",
                password = "password",
                firstName = "first",
                lastName = "last",
                age = 99,
                weight = 99.9
        )

        val user2 = User(
                username = "username2",
                password = "password",
                firstName = "first",
                lastName = "last",
                age = 99,
                weight = 99.9
        )

        userDao.insertAll(user1, user2)

        userDao.getAll()
                .test()
                .assertValue {
                    return@assertValue it.size == 2
                }
    }
}

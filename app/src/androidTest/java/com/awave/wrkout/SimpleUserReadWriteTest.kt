package com.awave.wrkout

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.awave.wrkout.db.FitnessTrackerDatabase
import com.awave.wrkout.db.User
import com.awave.wrkout.db.dao.UserDao
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
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
    fun createUserReturnUserId_shouldInsertUserSuccessfully() {
        val user1 = User(
                username = "username1",
                password = "password",
                firstName = "first",
                lastName = "last",
                age = 99,
                weight = 99.9
        )

        userDao.insert(user1)
                .test()
                .assertValue { it == 1.toLong() }
    }

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

    @Test
    fun getUserByUsername_shouldReturnUserSuccessfully() {
        val user1 = User(
                username = "username1",
                password = "password",
                firstName = "first",
                lastName = "last",
                age = 99,
                weight = 99.9
        )

        userDao.insertAll(user1)

        userDao.getByUsername(user1.username, user1.password)
                .test()
                .assertValue {
                    return@assertValue it.username == user1.username
                }
    }
}

package com.group15.fitnesstracker

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.group15.fitnesstracker.db.FitnessTrackerDatabase
import com.group15.fitnesstracker.db.SetExercise
import com.group15.fitnesstracker.db.Workout
import com.group15.fitnesstracker.db.WorkoutExercises
import com.group15.fitnesstracker.db.dao.WorkoutExercisesDao
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WorkoutExercisesTest {
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

        db.setExerciseDao()
                .insertAll_test(
                        SetExercise(name = "Squats", description = "Just squat"),
                        SetExercise(name = "Bench", description = "Just bench"),
                        SetExercise(name = "Deadlift", description = "Just lift"),
                        SetExercise(name = "OH Press", description = "Just press")
                )

        db.workoutExercisesDao()
                .insertAll_test(
                        WorkoutExercises(workoutId = 1, exerciseId = 1, sets = 5),
                        WorkoutExercises(workoutId = 1, exerciseId = 2, sets = 5),
                        WorkoutExercises(workoutId = 1, exerciseId = 4, sets = 5),


                        WorkoutExercises(workoutId = 2, exerciseId = 3, sets = 5),
                        WorkoutExercises(workoutId = 2, exerciseId = 2, sets = 5),
                        WorkoutExercises(workoutId = 2, exerciseId = 4, sets = 5)
                )
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getExercisesForWorkout1_shouldReturnListOfSetExercises() {
        workoutExercisesDao.getExercises(1)
                .test()
                .assertValue { it.size == 3 }
    }

    @Test
    fun getExercisesForWorkout2_shouldReturnListOfSetExercises() {
        workoutExercisesDao.getExercises(2)
                .test()
                .assertValue { it.size == 3 }
    }
}
package com.group15.fitnesstracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.group15.fitnesstracker.db.dao.UserDao
import com.group15.fitnesstracker.db.dao.WorkoutDao
import androidx.sqlite.db.SupportSQLiteDatabase
import com.group15.fitnesstracker.db.dao.SetExerciseDao
import com.group15.fitnesstracker.db.dao.WorkoutExercisesDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.Executors

const val DB_NAME = "fitness_tracker.db"
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f : () -> Unit) = IO_EXECUTOR.execute(f)

@Database(entities = [User::class, Workout::class, SetExercise::class, TimedExercise::class, WorkoutExercises::class], version = 1, exportSchema = false)
abstract class FitnessTrackerDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun setExerciseDao(): SetExerciseDao
    abstract fun workoutExercisesDao(): WorkoutExercisesDao

    companion object {
        @Volatile private var db: FitnessTrackerDatabase? = null

        fun instance(context: Context): FitnessTrackerDatabase =
                db ?: synchronized(this) {
                    db ?: buildDb(context).also { db = it }
                }

        private fun buildDb(context: Context): FitnessTrackerDatabase {

            return Room.databaseBuilder(context, FitnessTrackerDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            val dbInstance = instance(context)

                            // @TODO implement db population
                            Timber.d("populating db")
                            dbInstance.workoutDao()
                                    .insertAll(
                                            Workout("Strong 5x5 A", "Very"),
                                            Workout("Strong 5x5 B", "Strong"))
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()

                            dbInstance.setExerciseDao()
                                    .insertAll(
                                            SetExercise(name = "Squats", description = "Just squat"),
                                            SetExercise(name = "Bench", description = "Just bench"),
                                            SetExercise(name = "Deadlift", description = "Just lift"),
                                            SetExercise(name = "OH Press", description = "Just press")
                                    )
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()

                            dbInstance.workoutExercisesDao()
                                    .insertAll(
                                            WorkoutExercises(workoutId = 1, exerciseId = 1, reps = 5),
                                            WorkoutExercises(workoutId = 1, exerciseId = 2, reps = 5),
                                            WorkoutExercises(workoutId = 1, exerciseId = 4, reps = 5),


                                            WorkoutExercises(workoutId = 2, exerciseId = 3, reps = 5),
                                            WorkoutExercises(workoutId = 2, exerciseId = 2, reps = 5),
                                            WorkoutExercises(workoutId = 2, exerciseId = 4, reps = 5)
                                    )
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()
                        }
                    })
                    .build()
        }
    }
}

package com.group15.fitnesstracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.group15.fitnesstracker.db.dao.UserDao
import com.group15.fitnesstracker.db.dao.WorkoutDao
import androidx.sqlite.db.SupportSQLiteDatabase
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

@Database(entities = [User::class, Workout::class, SetExercise::class, TimedExercise::class], version = 1, exportSchema = false)
abstract class FitnessTrackerDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao

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

                            // @TODO implement db population
                            Timber.d("populating db")
                            instance(context).workoutDao().insert(Workout("Strong 5x5 A", "Very"))
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()
                            instance(context).workoutDao().insert(Workout("Strong 5x5 B", "Stronk"))
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()
                        }
                    })
                    .build()
        }
    }
}

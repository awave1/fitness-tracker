package com.group15.fitnesstracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.group15.fitnesstracker.db.dao.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import java.util.concurrent.Executors

const val DB_NAME = "fitness_tracker.db"
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f : () -> Unit) = IO_EXECUTOR.execute(f)

@Database(entities = [
    User::class, Workout::class, Goal::class,
    SetExercise::class, TimedExercise::class, WorkoutExercises::class, Set::class,
    BodyMeasureRecording::class, NutritionRecording::class, BodyPartMeasureRecording::class,
    History::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FitnessTrackerDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun setExerciseDao(): SetExerciseDao
    abstract fun workoutExercisesDao(): WorkoutExercisesDao
    abstract fun bodyRecordingDao(): BodyRecordingDao
    abstract fun nutritionRecordingDao(): NutritionRecordingDao
    abstract fun bodyPartRecordingDao(): BodyPartRecordingDao
    abstract fun historyDao(): HistoryDao
    abstract fun setDao(): SetDao
    abstract fun goalDao(): GoalDao

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
                                            WorkoutExercises(workoutId = 1, exerciseId = 1, numberOfSets = 1),
                                            WorkoutExercises(workoutId = 1, exerciseId = 2, numberOfSets = 1),
                                            WorkoutExercises(workoutId = 1, exerciseId = 4, numberOfSets = 1),


                                            WorkoutExercises(workoutId = 2, exerciseId = 3, numberOfSets = 5),
                                            WorkoutExercises(workoutId = 2, exerciseId = 2, numberOfSets = 5),
                                            WorkoutExercises(workoutId = 2, exerciseId = 4, numberOfSets = 5)
                                    )
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()

                            dbInstance.goalDao()
                                    .insertAll(
                                            Goal(goalId = 1, goalDescription = "Get to 200 lbs", completionDate = Date(), userId = 1),
                                            Goal(goalId = 2, goalDescription = "Eat chocolate chip cookies", completionDate = Date(), userId = 1)
                                    )
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()

                            dbInstance.nutritionRecordingDao()
                                    .insertNutritionRecordings(
                                            NutritionRecording(recordingId = 1,calories = 200.0,protein = 100.0,carbohydrate = 50.0,fat = 20.0,date = Date(), userId = 1) ,
                                            NutritionRecording(recordingId = 2,calories = 2000.0,protein = 1000.0,carbohydrate = 500.0,fat = 200.0,date = Date(), userId = 1)

                            ).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()

                            dbInstance.bodyRecordingDao()
                                    .insertBodyRecordings(
                                            BodyMeasureRecording(recordingId = 1,date = Date(),userId = 1,bodyFat = 5.0,weight = 150.0),
                                            BodyMeasureRecording(recordingId = 2,date = Date(),userId = 1,bodyFat = 0.0,weight = 0.0)
                                    ).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()

                            dbInstance.bodyPartRecordingDao()
                                    .insertBodyPartRecordings(
                                            BodyPartMeasureRecording(recordingId = 1,userId = 1,bodyPart = "Waist",bodyPartSize = 20.0,date = Date()),
                                            BodyPartMeasureRecording(recordingId = 2,userId = 1,bodyPart = "Spirit",bodyPartSize = 0.0,date = Date())

                                    ).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe()
                        }
                    })
                    .build()
        }
    }
}

package com.awave.wrkout.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.awave.wrkout.db.dao.*
import com.awave.wrkout.util.CryptoUtils
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.concurrent.Executors

const val DB_NAME = "fitness_tracker.db"
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f : () -> Unit) = IO_EXECUTOR.execute(f)

@Database(entities = [
    User::class, Workout::class, Goal::class,
    Trainer::class, Trains::class, ScheduleItem::class,
    SetExercise::class, TimedExercise::class, WorkoutExercises::class, Set::class,
    BodyMeasureRecording::class, NutritionRecording::class, BodyPartMeasureRecording::class, MicronutrientRecording::class,
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
    abstract fun trainerDao(): TrainerDao
    abstract fun trainsDao(): TrainsDao
    abstract fun scheduleItemDao(): ScheduleItemDao
    abstract fun micronutrientDao(): MicronutrientRecordingDao

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

                            Timber.d("populating db")

                            dbInstance.userDao()
                                    .insertAll(
                                            // id: 1
                                            User(username = "user1",
                                                 password = CryptoUtils.SHA256Hash("pass"),
                                                 firstName = "John", lastName = "Smith",
                                                 age = 22, weight = 240.0)
                                    )
                                    .subscribeOn(Schedulers.io())
                                    .subscribe()

                            dbInstance.trainerDao()
                                    .createTrainer(
                                            // id: 1
                                            Trainer(email = "email@email.com",
                                                    password = CryptoUtils.SHA256Hash("pass"),
                                                    firstName = "Mr",
                                                    lastName = "Strong"
                                    ))
                                    .subscribeOn(Schedulers.io())
                                    .subscribe()

                            dbInstance.workoutDao()
                                    .insertAll(
                                            Workout("Strong 5x5 A", "Basic 5x5 workout that includes Bench, Squat, and Overhead press"),
                                            Workout("Strong 5x5 B", "Basic 5x5 workout that includes Bench, Deadlift, and Overhead press"))
                                    .subscribeOn(Schedulers.io())
                                    .subscribe()

                            dbInstance.setExerciseDao()
                                    .insertAll(
                                            SetExercise(name = "Squat", description = "The squat is performed by squatting down with a weight held across the upper back under neck and standing up straight again."),
                                            SetExercise(name = "Bench press (Barbell)", description = "The bench press is an upper body strength training exercise that consists of pressing a weight upwards from a supine position."),
                                            SetExercise(name = "Deadlift", description = "The deadlift is performed by squatting down and lifting a weight off the floor with the hand until standing up straight again."),
                                            SetExercise(name = "Overhead Press", description = "The overhead press is a fantastic strength-and-muscle builder for the entire upper body and core")
                                    )
                                    .subscribeOn(Schedulers.io())
                                    .subscribe()

                            dbInstance.workoutExercisesDao()
                                    .insertAll(
                                            WorkoutExercises(workoutId = 1, exerciseId = 1, numberOfSets = 5),
                                            WorkoutExercises(workoutId = 1, exerciseId = 2, numberOfSets = 5),
                                            WorkoutExercises(workoutId = 1, exerciseId = 4, numberOfSets = 5),


                                            WorkoutExercises(workoutId = 2, exerciseId = 3, numberOfSets = 5),
                                            WorkoutExercises(workoutId = 2, exerciseId = 2, numberOfSets = 5),
                                            WorkoutExercises(workoutId = 2, exerciseId = 4, numberOfSets = 5)
                                    )
                                    .subscribeOn(Schedulers.io())
                                    .subscribe({}, {
                                        Timber.e(it)
                                    })
                        }
                    })
                    .build()
        }

        private fun createDate(date: String) = SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date)
    }
}

package com.group15.fitnesstracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.group15.fitnesstracker.db.dao.*
import com.group15.fitnesstracker.util.CryptoUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.text.SimpleDateFormat
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
    Trainer::class, Trains::class, ScheduleItem::class,
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
    abstract fun trainerDao(): TrainerDao
    abstract fun trainsDao(): TrainsDao
    abstract fun scheduleItemDao(): ScheduleItemDao

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

                            dbInstance.userDao()
                                    .insertAll(
                                            // id: 1
                                            User(username = "callmebiggy",
                                                 password = CryptoUtils.SHA256Hash("passwd"),
                                                 firstName = "Steven", lastName = "Mlilo",
                                                 age = 22, weight = 240.0),

                                            // id: 2
                                            User(username = "notkanye",
                                                    password = CryptoUtils.SHA256Hash("yzyszn"),
                                                    firstName = "Kanye", lastName = "East",
                                                    age = 40, weight = 123.0),

                                            // id: 3
                                            User(username = "putin",
                                                    password = CryptoUtils.SHA256Hash("lol"),
                                                    firstName = "Vova", lastName = "Putin",
                                                    age = 99, weight = 999.9)
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

                            dbInstance.scheduleItemDao()
                                    .createItems(
                                            ScheduleItem(workoutId = 1, trainerId = 1, userId = 1,
                                                    from = createDate("05/12/2018 08:00"), to = createDate("05/12/2018 10:00")),
                                            ScheduleItem(workoutId = 2, trainerId = 1, userId = 1,
                                                    from = createDate("06/12/2018 08:00"), to = createDate("06/12/2018 10:00")),
                                            ScheduleItem(workoutId = 1, trainerId = 1, userId = 3,
                                                    from = createDate("05/12/2018 16:00"), to = createDate("05/12/2018 18:00")),
                                            ScheduleItem(workoutId = 2, trainerId = 1, userId = 3,
                                                    from = createDate("06/12/2018 16:00"), to = createDate("06/12/2018 18:00"))
                                    )
                                    .subscribeOn(Schedulers.io())
                                    .subscribe()
                        }
                    })
                    .build()
        }

        private fun createDate(date: String) = SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date)
    }
}

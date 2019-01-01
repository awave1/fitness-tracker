package com.awave.wrkout.dashboard.schedule

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.widget.*
import com.awave.wrkout.R
import com.awave.wrkout.db.DbInjection
import com.awave.wrkout.db.ScheduleItem
import com.awave.wrkout.db.User
import com.awave.wrkout.db.Workout
import com.awave.wrkout.util.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

class CreateScheduleItemDialogFactory {
    companion object {
        @SuppressLint("CheckResult")
        fun create(
                trainerId: Int,
                context: Context,
                onSave: () -> Unit
        ): AlertDialog {
            val builder = AlertDialog.Builder(context)
            val view = View.inflate(context, R.layout.create_schedule_item, null)

            val selectUser = view.findViewById<Spinner>(R.id.selectUser)
            val selectWorkout = view.findViewById<Spinner>(R.id.selectWorkout)
            val fromDate = view.findViewById<EditText>(R.id.fromDate)
            val fromTime = view.findViewById<EditText>(R.id.fromTime)
            val toDate = view.findViewById<EditText>(R.id.toDate)
            val toTime = view.findViewById<EditText>(R.id.toTime)

            val calendarFrom = Calendar.getInstance()
            val calendarTo = Calendar.getInstance()
            var pickedDateFrom = Date()
            var pickedDateTo = Date()

            var userList: List<String>
            var workoutList: List<String>
            var usersObjList: List<User> = listOf()
            var workoutObjList: List<Workout> = listOf()

            var selectedUser: User? = null
            var selectedWorkout: Workout? = null

            DbInjection.provideUserDao(context)
                    .getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { users ->
                        userList = users.map { "${it.firstName} ${it.lastName}" }
                        val userAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, userList)
                        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        selectUser.adapter = userAdapter
                        usersObjList = users
                    }

            DbInjection.provideWorkoutDao(context)
                    .getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { workouts ->
                        workoutList = workouts.map { it.name }
                        val workoutAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, workoutList)
                        workoutAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        selectWorkout.adapter = workoutAdapter
                        workoutObjList = workouts
                    }

            fromDate.setOnClickListener {
                DatePickerDialog(
                        it.context,
                        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                            calendarFrom.set(Calendar.YEAR, year)
                            calendarFrom.set(Calendar.MONTH, month)
                            calendarFrom.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                            fromDate.hint = Utils.formatDate(calendarFrom.time, format = "dd/MM/yyyy")
                        },
                        calendarFrom.get(Calendar.YEAR),
                        calendarFrom.get(Calendar.MONTH),
                        calendarFrom.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            fromTime.setOnClickListener {
                TimePickerDialog (
                        it.context,
                        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                            calendarFrom.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            calendarFrom.set(Calendar.MINUTE, minute)

                            fromTime.hint = Utils.formatDate(calendarFrom.time, format = "HH:mm")
                        },
                        calendarFrom.get(Calendar.HOUR),
                        calendarFrom.get(Calendar.MINUTE),
                        true
                ).show()
            }

            toDate.setOnClickListener {
                DatePickerDialog(
                        it.context,
                        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                            calendarTo.set(Calendar.YEAR, year)
                            calendarTo.set(Calendar.MONTH, month)
                            calendarTo.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                            toDate.hint = Utils.formatDate(calendarTo.time, format = "dd/MM/yyyy")
                        },
                        calendarTo.get(Calendar.YEAR),
                        calendarTo.get(Calendar.MONTH),
                        calendarTo.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            toTime.setOnClickListener {
                TimePickerDialog (
                        it.context,
                        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                            calendarTo.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            calendarTo.set(Calendar.MINUTE, minute)

                            toTime.hint = Utils.formatDate(calendarTo.time, format = "HH:mm")
                        },
                        calendarTo.get(Calendar.HOUR),
                        calendarTo.get(Calendar.MINUTE),
                        true
                ).show()
            }

            builder.setView(view)
                    // Add action buttons
                    .setPositiveButton(R.string.save_recording_ok) { dialog, id ->
                        pickedDateFrom = calendarFrom.time
                        pickedDateTo = calendarTo.time
                        selectedWorkout = workoutObjList[selectWorkout.selectedItemPosition]
                        selectedUser = usersObjList[selectUser.selectedItemPosition]

                        DbInjection.provideScheduleItemDao(context)
                                .createScheduleItem(ScheduleItem(
                                        workoutId = selectedWorkout?.workoutId!!,
                                        userId = selectedUser?.id!!,
                                        from = pickedDateFrom,
                                        to = pickedDateTo,
                                        trainerId = trainerId
                                ))
                                .subscribeOn(Schedulers.io())
                                .subscribe {
                                    Timber.d("Schedule created! ${selectedUser?.id} ${selectedWorkout?.workoutId}")
                                    onSave()
                                }

                    }
                    .setNegativeButton(R.string.cancel) { dialog, id -> dialog.cancel() }
            return builder.create()
        }
    }
}
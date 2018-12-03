package com.group15.fitnesstracker.dashboard.tracker

import android.content.Context
import android.widget.Toast
import com.group15.fitnesstracker.db.BodyMeasureRecording
import com.group15.fitnesstracker.db.BodyPartMeasureRecording
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.db.NutritionRecording
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateRecordingPresenter(val view: CreateRecordingContract.View, private val context: Context?): CreateRecordingContract.Presenter {
    init {
        view.presenter = this
    }

    private var bodyRecordings = mutableListOf<BodyMeasureRecording>()
    private var nutritionRecordings = mutableListOf<NutritionRecording>()
    private var bodyPartRecordings = mutableListOf<BodyPartMeasureRecording>()

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createBodyRecording(bodyFat: String?, bodyWeight: String?, userId: Int?, callback: (BodyMeasureRecording) -> Unit) {
        context?.let {
            val recording = BodyMeasureRecording(bodyFat = bodyFat?.toDouble(), weight = bodyWeight?.toDouble(), userId = userId)
            DbInjection.provideBodyRecordingDao(it)
                    .insertBodyRecordings(recording)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Toast.makeText(it, "Saved", Toast.LENGTH_SHORT).show()
                        callback(recording)
                    }
        }
    }

    override fun loadBodyRecordings(userId: Int?) {
        userId?.let { id ->
            context?.let { ctx ->
                DbInjection.provideBodyRecordingDao(ctx)
                        .loadUserBodyRecordings(userId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            bodyRecordings = it
                            (view as CreateRecordingContract.BodyTrackerView).showBodyRecordings(it)
                        }
            }
        }
    }

    override fun createNutritionRecording(calories: Double?,
                                          protein: Double?,
                                          carbohydrate: Double?,
                                          fat: Double?,
                                          userId: Int?, callback: (NutritionRecording) -> Unit) {
        context?.let { ctx ->
            userId?.let { it ->
                val record = NutritionRecording(
                    userId = userId, calories = calories, protein = protein, carbohydrate = carbohydrate, fat = fat
                )

                DbInjection.provideNutritionRecordingDao(ctx)
                        .insertNutritionRecordings(record)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { callback(record) }
            }
        }
    }

    override fun loadNutritionRecordings(userId: Int?) {
        context?.let { ctx ->
            userId?.let { id ->
               DbInjection.provideNutritionRecordingDao(ctx)
                       .getNutritionRecordingsForUser(userId)
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe {
                           nutritionRecordings = it
                           (view as CreateRecordingContract.NutritionTrackerView).showNutritionRecordings(it)
                       }
            }
        }
    }

    override fun loadBodyPartRecordings(userId: Int?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
package com.group15.fitnesstracker.dashboard.tracker

import android.content.Context
import android.widget.Toast
import com.group15.fitnesstracker.db.BodyMeasureRecording
import com.group15.fitnesstracker.db.DbInjection
import com.group15.fitnesstracker.onboarding.createUser.CreateUserContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class CreateRecordingPresenter(val view: CreateRecordingContract.BodyTrackerView, private val context: Context?): CreateRecordingContract.Presenter {
    init {
        view.presenter = this
    }

    private var recorings = mutableListOf<BodyMeasureRecording>()

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
                            recorings = it
                            view.showBodyRecordings(it)
                        }
            }
        }
    }

}
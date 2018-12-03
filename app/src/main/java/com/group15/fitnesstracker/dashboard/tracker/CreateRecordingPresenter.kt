package com.group15.fitnesstracker.dashboard.tracker

import android.content.Context
import android.widget.Toast
import com.group15.fitnesstracker.db.BodyMeasureRecording
import com.group15.fitnesstracker.db.DbInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class CreateRecordingPresenter(private val context: Context?): CreateRecordingContract.Presenter {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createBodyRecording(bodyFat: String?, bodyWeight: String?) {
        context?.let {
            DbInjection.provideBodyRecordingDao(it)
                    .insertBodyRecordings(BodyMeasureRecording(Date(), bodyFat?.toDouble()!!, bodyWeight?.toDouble()!!))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Toast.makeText(it, "Saved", Toast.LENGTH_SHORT).show() }
        }
    }

}
package com.group15.fitnesstracker

import android.app.Application
import timber.log.Timber

class FitnessTracketApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
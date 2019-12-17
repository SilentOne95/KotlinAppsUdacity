package com.example.activitystate

import android.app.Application
import timber.log.Timber

class StateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
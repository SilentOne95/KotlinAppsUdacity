package com.example.databaseapp.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.databaseapp.database.SleepDatabaseDao

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(val database: SleepDatabaseDao, application: Application) :
    AndroidViewModel(application) {
}
package com.example.offlineapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.offlineapp.database.getDatabase
import com.example.offlineapp.repository.VideosRepository
import retrofit2.HttpException

class RefreshDataWorker (appcontext: Context, params: WorkerParameters): CoroutineWorker(appcontext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = VideosRepository(database)

        return try {
            repository.refreshVideos()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}
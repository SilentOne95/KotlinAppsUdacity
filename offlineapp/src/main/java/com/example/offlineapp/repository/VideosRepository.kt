package com.example.offlineapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.offlineapp.database.VideosDatabase
import com.example.offlineapp.database.asDomainModel
import com.example.offlineapp.domain.Video
import com.example.offlineapp.network.Network
import com.example.offlineapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val database: VideosDatabase) {

    val videos: LiveData<List<Video>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = Network.devbytes.getPlaylist().await()
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}
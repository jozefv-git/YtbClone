package com.jozefv.ytbclone.data.videos

import com.jozefv.ytbclone.domain.data_source.VideoDataSource
import com.jozefv.ytbclone.domain.repository.VideoRepository
import com.jozefv.ytbclone.domain.model.VideoResultUi

class VideoRepositoryImpl(private val videoDataSource: VideoDataSource) : VideoRepository {
    // In reality, these may be suspending calls from the API
    override fun getVideos(ascendingOrder: Boolean): List<VideoResultUi> {
        return if (ascendingOrder) {
            videoDataSource.getVideos()
                .sortedBy { it.title }
        } else {
            videoDataSource.getVideos()
                .sortedByDescending { it.title }
        }
    }

    override fun searchVideos(query: String): List<VideoResultUi> {
        val lowercaseQuery = query.lowercase()
        return videoDataSource.getVideos()
            .filter {
                it.title.lowercase()
                    .contains(lowercaseQuery)
            }
    }
}
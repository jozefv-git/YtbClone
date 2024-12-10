package com.jozefv.ytbclone.data.videos

import com.jozefv.ytbclone.domain.VideoDataSource
import com.jozefv.ytbclone.domain.VideoRepository
import com.jozefv.ytbclone.domain.model.VideoResultUi

class VideoRepositoryImpl(private val videoDataSource: VideoDataSource) : VideoRepository {
    // In reality, these may be suspending calls from the API
    override fun getVideos(ascendingOrder: Boolean): List<VideoResultUi> {
        return if (ascendingOrder) {
            videoDataSource.getVideos()
                .sortedBy { it.title }
                .map { it.toVideoResultUi() }
        } else {
            videoDataSource.getVideos()
                .sortedByDescending { it.title }
                .map { it.toVideoResultUi() }
        }
    }

    override fun searchVideos(query: String): List<VideoResultUi> {
        val lowercaseQuery = query.lowercase()
        return videoDataSource.getVideos()
            .filter {
                it.title.lowercase()
                    .contains(lowercaseQuery)
            }
            .map { it.toVideoResultUi() }
    }
}
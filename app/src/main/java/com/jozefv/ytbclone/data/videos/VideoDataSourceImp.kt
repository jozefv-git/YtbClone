package com.jozefv.ytbclone.data.videos

import com.jozefv.ytbclone.domain.VideoDataSource

class VideoDataSourceImp(private val videos: List<VideoResult>): VideoDataSource {
    override fun getVideos(): List<VideoResult>{
        return videos
    }
}
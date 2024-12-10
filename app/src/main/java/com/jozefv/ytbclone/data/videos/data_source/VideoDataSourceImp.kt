package com.jozefv.ytbclone.data.videos.data_source

import com.jozefv.ytbclone.data.videos.model.VideoResult
import com.jozefv.ytbclone.data.videos.model.toVideoResultUi
import com.jozefv.ytbclone.domain.data_source.VideoDataSource
import com.jozefv.ytbclone.domain.model.VideoResultUi

class VideoDataSourceImp(private val videoSource: List<VideoResult>) : VideoDataSource {
    override fun getVideos(): List<VideoResultUi> {
        return videoSource.map { it.toVideoResultUi() }
    }
}
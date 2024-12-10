package com.jozefv.ytbclone.domain.data_source

import com.jozefv.ytbclone.domain.model.VideoResultUi

interface VideoDataSource {
    fun getVideos(): List<VideoResultUi>
}
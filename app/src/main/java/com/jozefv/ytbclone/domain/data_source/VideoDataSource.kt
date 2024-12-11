package com.jozefv.ytbclone.domain.data_source

import com.jozefv.ytbclone.domain.model.Video

interface VideoDataSource {
    fun getVideos(): List<Video>
}
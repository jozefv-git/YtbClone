package com.jozefv.ytbclone.domain

import com.jozefv.ytbclone.data.videos.VideoResult

interface VideoDataSource {
    fun getVideos(): List<VideoResult>
}
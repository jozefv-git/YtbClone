package com.jozefv.ytbclone.domain

import com.jozefv.ytbclone.domain.model.VideoResultUi

interface VideoRepository {
    fun getVideos(ascendingOrder: Boolean = true): List<VideoResultUi>
    fun searchVideos(query: String = ""): List<VideoResultUi>
}
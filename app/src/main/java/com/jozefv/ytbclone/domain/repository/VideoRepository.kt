package com.jozefv.ytbclone.domain.repository

import com.jozefv.ytbclone.domain.model.Video

interface VideoRepository {
    fun getVideos(ascendingOrder: Boolean = true): List<Video>
    fun searchVideos(query: String = ""): List<Video>
}
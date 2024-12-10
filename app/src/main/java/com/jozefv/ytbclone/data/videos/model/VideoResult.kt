package com.jozefv.ytbclone.data.videos.model

// This class represents our result obtained from our data source - ex. API
data class VideoResult(
    val title: String,
    val description: String,
    val author: String,
    val publishedDate: String,
    val authorThumbnail: Int,
    val image: Int
)

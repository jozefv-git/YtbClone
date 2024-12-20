package com.jozefv.ytbclone.domain.model

// In reality, this may differ based on our received result
// With domain model class, we try to expose only data what are important for our presentation layer
data class Video(
    val title: String,
    val description: String,
    val author: String,
    val publishedDate: String,
    val authorThumbnail: Int,
    val image: Int
)

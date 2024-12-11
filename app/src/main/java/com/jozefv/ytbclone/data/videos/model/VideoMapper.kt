package com.jozefv.ytbclone.data.videos.model

import com.jozefv.ytbclone.domain.model.Video

fun VideoResult.toVideo(): Video {
    return Video(
        title = title,
        description = description,
        author = author,
        publishedDate = publishedDate,
        authorThumbnail = authorThumbnail,
        image = image
    )
}
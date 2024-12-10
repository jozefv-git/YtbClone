package com.jozefv.ytbclone.data.videos

import com.jozefv.ytbclone.domain.model.VideoResultUi

fun VideoResult.toVideoResultUi(): VideoResultUi {
    return VideoResultUi(
        title = title,
        description = description,
        author = author,
        publishedDate = publishedDate,
        authorThumbnail = authorThumbnail,
        image = image
    )
}
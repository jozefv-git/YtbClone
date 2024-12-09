package com.jozefv.ytbclone.presentation.subscriptions.mappers

import com.jozefv.ytbclone.domain.VideoResultUi

// This conversion is needed to be able send parcelable between list and detail list screen
// when working with adaptive-layout
// for more check: https://developer.android.com/develop/ui/compose/layouts/adaptive/list-detail
fun VideoResultUi.toVideoResultUiParcelize(): VideoResultUiParcelize {
    return VideoResultUiParcelize(
        title = title,
        description = description,
        author = author,
        publishedDate = publishedDate,
        authorThumbnail = authorThumbnail,
        image = image
    )
}
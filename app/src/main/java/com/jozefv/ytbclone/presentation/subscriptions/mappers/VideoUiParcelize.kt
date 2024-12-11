package com.jozefv.ytbclone.presentation.subscriptions.mappers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoUiParcelize(
    val title: String,
    val description: String,
    val author: String,
    val publishedDate: String,
    val authorThumbnail: Int,
    val image: Int
) : Parcelable

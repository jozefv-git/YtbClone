package com.jozefv.ytbclone.presentation.subscriptions

import com.jozefv.ytbclone.presentation.subscriptions.mappers.VideoUiParcelize

data class SubscriptionState(
    val listOfSubscriptions: List<VideoUiParcelize> = emptyList(),
    val ascendingOrder: Boolean = true,
    val query: String = "",
    val itemPosition: Int = 0
)

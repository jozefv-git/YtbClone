package com.jozefv.ytbclone.presentation.subscriptions

import com.jozefv.ytbclone.presentation.subscriptions.mappers.VideoResultUiParcelize

data class SubscriptionState(val listOfSubscriptions: List<VideoResultUiParcelize> = emptyList())

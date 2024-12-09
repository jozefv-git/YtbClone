package com.jozefv.ytbclone.presentation.subscriptions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SubscriptionViewModel: ViewModel() {
    var state by mutableStateOf(SubscriptionState())
        private set


}
package com.jozefv.ytbclone.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    fun onAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.onLogoutClick -> {
                // TODO: Perform logout here
            }
        }
    }
}
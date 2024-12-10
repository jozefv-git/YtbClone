package com.jozefv.ytbclone.presentation.profile

sealed interface ProfileEvent {
    data object OnLogoutSuccess: ProfileEvent
}
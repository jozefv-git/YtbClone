package com.jozefv.ytbclone.presentation.login

// Events from our VM to our UI
sealed interface LoginEvent {
    data object OnLoginSuccess: LoginEvent
}
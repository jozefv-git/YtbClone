package com.jozefv.ytbclone.presentation.profile

sealed interface ProfileAction {
    data object onLogoutClick: ProfileAction
}
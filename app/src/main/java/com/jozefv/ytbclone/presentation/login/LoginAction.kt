package com.jozefv.ytbclone.presentation.login

sealed interface LoginAction {
    data object OnLoginCLicked: LoginAction
}
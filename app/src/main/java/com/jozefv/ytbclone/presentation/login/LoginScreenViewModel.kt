package com.jozefv.ytbclone.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginScreenViewModel : ViewModel() {

    var state by mutableStateOf(LoginScreenState())
        private set

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnLoginCLicked -> {
                // TODO: DO login
            }
        }
    }
}
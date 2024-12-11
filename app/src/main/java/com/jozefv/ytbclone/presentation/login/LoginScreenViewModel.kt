package com.jozefv.ytbclone.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jozefv.ytbclone.DispatcherProvider
import com.jozefv.ytbclone.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val authRepository: AuthRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    var state by mutableStateOf(LoginScreenState())
        private set
    private val _channel = Channel<LoginEvent>()
    val channel = _channel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnLoginCLicked -> {
                viewModelScope.launch(dispatcherProvider.mainImmediate) {
                    // We don't need to call is logging false, as VM will be destroyed and state reseted
                    // when navigation is performed
                    state = state.copy(isLogging = true)
                    // For better UI delay login, to see the progress indicator
                    delay(2000)
                    authRepository.login(true)
                    if (authRepository.isLoggedIn()) {
                        _channel.send(LoginEvent.OnLoginSuccess)
                    }
                }
            }
        }
    }
}
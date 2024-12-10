package com.jozefv.ytbclone.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jozefv.ytbclone.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    private val _channel = Channel<ProfileEvent>()
    val channel = _channel.receiveAsFlow()

    init {
        viewModelScope.launch {
            authRepository.getProfile()?.let { profile ->
                state = state.copy(profile = profile)
            }
        }
    }

    fun onAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.onLogoutClick -> {
                viewModelScope.launch {
                    // logout user
                    authRepository.login(false)
                    // User is not loggedIn - send success logout event
                    if (!authRepository.isLoggedIn()) {
                        _channel.send(ProfileEvent.OnLogoutSuccess)
                    }
                }
            }
        }
    }
}
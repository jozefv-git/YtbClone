package com.jozefv.ytbclone.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jozefv.ytbclone.DispatcherProvider
import com.jozefv.ytbclone.domain.repository.AuthRepository
import com.jozefv.ytbclone.domain.repository.ProfileRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    private val _channel = Channel<ProfileEvent>()
    val channel = _channel.receiveAsFlow()

    init {
        viewModelScope.launch(dispatcherProvider.mainImmediate) {
            if (authRepository.isLoggedIn()) {
                state = state.copy(profile = profileRepository.getProfile())
            }
        }
    }

    fun onAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.onLogoutClick -> {
                viewModelScope.launch(dispatcherProvider.mainImmediate) {
                    // logout user
                    authRepository.logout()
                    // User is not loggedIn - send success logout event
                    if (!authRepository.isLoggedIn()) {
                        _channel.send(ProfileEvent.OnLogoutSuccess)
                    }
                }
            }
        }
    }
}
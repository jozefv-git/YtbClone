package com.jozefv.ytbclone

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jozefv.ytbclone.domain.SessionStorage
import kotlinx.coroutines.launch

class MainViewModel(
    sessionStorage: SessionStorage,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch(dispatcherProvider.mainImmediate) {
            state = state.copy(isCheckingAuth = true)
            state = state.copy(isLoggedIn = sessionStorage.isAuthenticated())
            state = state.copy(isCheckingAuth = false)
        }
    }
}
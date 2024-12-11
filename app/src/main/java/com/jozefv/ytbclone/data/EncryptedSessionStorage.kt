package com.jozefv.ytbclone.data

import android.content.SharedPreferences
import com.jozefv.ytbclone.DispatcherProvider
import com.jozefv.ytbclone.domain.SessionStorage
import kotlinx.coroutines.withContext

class EncryptedSessionStorage(
    private val sharedPrefs: SharedPreferences,
    private val dispatcherProvider: DispatcherProvider
) : SessionStorage {
    override suspend fun isAuthenticated(): Boolean {
        return withContext(dispatcherProvider.io) {
            sharedPrefs.getBoolean(KEY_AUTH, false)
        }
    }

    override suspend fun login(status: Boolean) {
        withContext(dispatcherProvider.io) {
            sharedPrefs
                .edit()
                .putBoolean(KEY_AUTH, status)
                .commit() // This can block our code
        }
    }

    companion object {
        private const val KEY_AUTH = "KEY_AUTH"
    }
}
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

    override suspend fun login() {
        withContext(dispatcherProvider.io) {
            sharedPrefs
                .edit()
                .putBoolean(KEY_AUTH, true)
                .commit() // This can block our code
        }
    }

    override suspend fun logout() {
        withContext(dispatcherProvider.io){
            sharedPrefs
                .edit()
                .putBoolean(KEY_AUTH,false)
                .commit()
        }
    }

    companion object {
        private const val KEY_AUTH = "KEY_AUTH"
    }
}
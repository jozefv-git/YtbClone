package com.jozefv.ytbclone.data

import android.content.SharedPreferences
import com.jozefv.ytbclone.domain.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EncryptedSessionStorage(private val sharedPrefs: SharedPreferences) : SessionStorage {
    override suspend fun isLoggedIn(): Boolean {
        return withContext(Dispatchers.IO) {
            sharedPrefs.getBoolean(KEY_AUTH, false)
        }
    }

    override suspend fun logIn(status: Boolean) {
        withContext(Dispatchers.IO) {
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
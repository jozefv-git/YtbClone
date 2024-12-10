package com.jozefv.ytbclone.domain

// Abstraction what the encrypted session storage should actually do
interface SessionStorage {
    // These can take some time, better to have it suspend
    suspend fun isLoggedIn(): Boolean
    suspend fun logIn(status: Boolean)
}
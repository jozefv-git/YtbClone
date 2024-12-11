package com.jozefv.ytbclone.domain

// Abstraction what the encrypted session storage should actually do
interface SessionStorage {
    // These can take some time, better to have it suspend
    suspend fun isAuthenticated(): Boolean
    suspend fun login(status: Boolean)
}
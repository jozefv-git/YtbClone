package com.jozefv.ytbclone.domain.repository

interface AuthRepository {
    suspend fun login(status: Boolean)
    suspend fun isLoggedIn(): Boolean
}
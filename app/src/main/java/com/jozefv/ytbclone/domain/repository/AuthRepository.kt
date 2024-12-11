package com.jozefv.ytbclone.domain.repository

interface AuthRepository {
    suspend fun isLoggedIn(): Boolean
    suspend fun login()
    suspend fun logout()
}
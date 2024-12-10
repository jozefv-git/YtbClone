package com.jozefv.ytbclone.domain.repository

import com.jozefv.ytbclone.domain.model.ProfileUi

interface AuthRepository {
    suspend fun login(status: Boolean)
    suspend fun isLoggedIn(): Boolean
    suspend fun getProfile(): ProfileUi?
}
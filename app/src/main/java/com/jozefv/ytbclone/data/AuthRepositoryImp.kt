package com.jozefv.ytbclone.data

import com.jozefv.ytbclone.domain.repository.AuthRepository
import com.jozefv.ytbclone.domain.SessionStorage

// Just an example
// Here would be a logic related to login - ex. network call to check if user is valid
class AuthRepositoryImp(
    private val sessionStorage: SessionStorage
) : AuthRepository {
    override suspend fun isLoggedIn(): Boolean {
        return sessionStorage.isAuthenticated()
    }

    override suspend fun login() {
        sessionStorage.login()
    }

    override suspend fun logout() {
        sessionStorage.logout()
    }
}
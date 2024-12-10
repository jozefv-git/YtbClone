package com.jozefv.ytbclone.data

import com.jozefv.ytbclone.domain.repository.AuthRepository
import com.jozefv.ytbclone.domain.repository.ProfileRepository
import com.jozefv.ytbclone.domain.SessionStorage
import com.jozefv.ytbclone.domain.model.ProfileUi

// Just an example
// Here would be a logic related to login - ex. network call to check if user is valid
class AuthRepositoryImp(
    private val profileRepository: ProfileRepository,
    private val sessionStorage: SessionStorage
) : AuthRepository {
    override suspend fun login(status: Boolean) {
        sessionStorage.logIn(status)
    }

    override suspend fun isLoggedIn(): Boolean {
        return sessionStorage.isLoggedIn()
    }

    override suspend fun getProfile(): ProfileUi? {
        return if (sessionStorage.isLoggedIn()) {
            profileRepository.getProfile()
        } else null
    }
}
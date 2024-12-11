package com.jozefv.ytbclone.domain.repository

import com.jozefv.ytbclone.domain.model.Profile

interface ProfileRepository {
    fun getProfile(): Profile
}
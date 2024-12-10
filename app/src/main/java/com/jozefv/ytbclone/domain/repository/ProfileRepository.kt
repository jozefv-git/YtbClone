package com.jozefv.ytbclone.domain.repository

import com.jozefv.ytbclone.domain.model.ProfileUi

interface ProfileRepository {
    fun getProfile(): ProfileUi
}
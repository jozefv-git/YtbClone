package com.jozefv.ytbclone.domain.data_source

import com.jozefv.ytbclone.domain.model.Profile

interface ProfileDataSource {
    fun getProfile(): Profile
}
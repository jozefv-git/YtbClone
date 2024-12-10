package com.jozefv.ytbclone.domain.data_source

import com.jozefv.ytbclone.domain.model.ProfileUi

interface ProfileDataSource {
    fun getProfile(): ProfileUi
}
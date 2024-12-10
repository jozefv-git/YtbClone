package com.jozefv.ytbclone.data.profile.data_source

import com.jozefv.ytbclone.data.profile.model.ProfileResult
import com.jozefv.ytbclone.data.profile.model.toProfileUi
import com.jozefv.ytbclone.domain.data_source.ProfileDataSource
import com.jozefv.ytbclone.domain.model.ProfileUi

class ProfileDataSourceImpl(private val profileSource: ProfileResult): ProfileDataSource {
    override fun getProfile(): ProfileUi {
        return profileSource.toProfileUi()
    }
}
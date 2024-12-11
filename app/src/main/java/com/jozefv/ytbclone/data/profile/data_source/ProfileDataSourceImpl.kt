package com.jozefv.ytbclone.data.profile.data_source

import com.jozefv.ytbclone.data.profile.model.ProfileResult
import com.jozefv.ytbclone.data.profile.model.toProfile
import com.jozefv.ytbclone.domain.data_source.ProfileDataSource
import com.jozefv.ytbclone.domain.model.Profile

class ProfileDataSourceImpl(private val profileSource: ProfileResult): ProfileDataSource {
    override fun getProfile(): Profile {
        return profileSource.toProfile()
    }
}
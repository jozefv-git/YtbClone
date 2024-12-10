package com.jozefv.ytbclone.data.profile

import com.jozefv.ytbclone.domain.data_source.ProfileDataSource
import com.jozefv.ytbclone.domain.repository.ProfileRepository
import com.jozefv.ytbclone.domain.model.ProfileUi

class ProfileRepositoryImpl(private val profileDataSource: ProfileDataSource): ProfileRepository {
    override fun getProfile(): ProfileUi {
        return profileDataSource.getProfile()
    }
}
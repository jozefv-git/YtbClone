package com.jozefv.ytbclone.data.profile.model

import com.jozefv.ytbclone.domain.model.ProfileUi

fun ProfileResult.toProfileUi(): ProfileUi {
    return ProfileUi(
        email = email,
        accountName = accountName,
        bio = bio,
        image = image
    )
}
package com.jozefv.ytbclone.data.profile

import com.jozefv.ytbclone.data.Profile
import com.jozefv.ytbclone.domain.ProfileUi

fun Profile.toProfileUi(): ProfileUi {
    return ProfileUi(
        email = email,
        accountName = accountName,
        bio = bio,
        image = image
    )
}
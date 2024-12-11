package com.jozefv.ytbclone.data.profile.model

import com.jozefv.ytbclone.domain.model.Profile

fun ProfileResult.toProfile(): Profile {
    return Profile(
        email = email,
        accountName = accountName,
        bio = bio,
        image = image
    )
}
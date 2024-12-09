package com.jozefv.ytbclone.data.profile

import com.jozefv.ytbclone.R

data class Profile(
    val email: String,
    val accountName: String,
    val bio: String,
    val image: Int
)

val profile = Profile(
    email = "john.doe@mail.com",
    accountName = "John Doe",
    bio = "Person who is trying to solve a different kind of challenges. " +
            "Lover of nature, food and life.",
    R.drawable.image18
)

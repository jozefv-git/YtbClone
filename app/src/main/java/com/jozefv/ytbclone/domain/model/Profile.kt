package com.jozefv.ytbclone.domain.model

// This can be accessed by our UI, so we don't break our layer pattern
data class Profile(
    val email: String,
    val accountName: String,
    val bio: String,
    val image: Int
)

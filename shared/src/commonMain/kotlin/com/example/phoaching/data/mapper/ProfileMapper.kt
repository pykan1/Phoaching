package com.example.phoaching.data.mapper

import com.example.phoaching.data.models.fastAuth.ProfileResponse
import com.example.phoaching.domain.models.ProfileUI

fun ProfileResponse.toUI(): ProfileUI {
    return ProfileUI(
        userId = guid.orEmpty(),
        username = username.orEmpty(),
        lastName = lastName.orEmpty(),
        firstName = firstName.orEmpty(),
        email = email.orEmpty(),
        phone = phone.orEmpty(),
        avatar = avatar.orEmpty(),
        city = city.orEmpty(),
        account = account.orEmpty()
    )
}
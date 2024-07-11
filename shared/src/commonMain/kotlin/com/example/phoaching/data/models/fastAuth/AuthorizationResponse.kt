package com.example.phoaching.data.models.fastAuth

import kotlinx.serialization.Serializable


@Serializable
class AuthorizationResponse (
    val profile: ProfileResponse?,
    val token: String?,
)

@Serializable
class ProfileResponse(
    val guid: String?,
    val username: String?,
    val firstName: String?,
    val account: String?,
    val lastName: String?,
    val city: String?,
    val phone: String?,
    val email: String?,
    val avatar: String?,
)
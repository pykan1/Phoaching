package com.example.phoaching.data.models.login

import kotlinx.serialization.Serializable


@Serializable
class LoginRequest(
    val username: String,
    val password: String
)
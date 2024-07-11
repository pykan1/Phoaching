package com.example.phoaching.data.models.register

import kotlinx.serialization.Serializable


@Serializable
class RegisterRequest (
    val userName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val repeatPassword: String,
    val tel: String,
    val city: String
)
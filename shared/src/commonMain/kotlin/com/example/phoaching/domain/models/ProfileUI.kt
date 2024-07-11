package com.example.phoaching.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProfileUI (
    val userId: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val account: String,
    val city: String,
    val phone: String,
    val email: String,
    val avatar: String,
)
package com.example.phoaching.data.models.fastAuth

import kotlinx.serialization.Serializable


@Serializable
class CodeRequest (
    val id: String,
    val code: String
)
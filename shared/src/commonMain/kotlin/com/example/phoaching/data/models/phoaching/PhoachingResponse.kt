package com.example.phoaching.data.models.phoaching

import kotlinx.serialization.Serializable


@Serializable
class PhoachingResponse(
    val id: String?,
    val title: String?,
    val author: AuthorResponse?
)

@Serializable
class AuthorResponse(
    val id: String?,
    val firstName: String?,
    val lastName: String?
)
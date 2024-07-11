package com.example.phoaching.data.models.getCode

import kotlinx.serialization.Serializable


@Serializable
class GetCodeResponse (
    val id: String?,
    val expire: Int?,
    val target: String?,
    val codeLength: Int?,
    val targetType: String?,
    val sentStamp: Int?,
)
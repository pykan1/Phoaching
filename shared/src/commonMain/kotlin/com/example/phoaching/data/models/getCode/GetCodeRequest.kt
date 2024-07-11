package com.example.phoaching.data.models.getCode

import kotlinx.serialization.Serializable


@Serializable
class GetCodeRequest (
    val target: String,
    val confirmationType: ConfirmationTypeRequest? = ConfirmationTypeRequest.FAST_AUTH
)
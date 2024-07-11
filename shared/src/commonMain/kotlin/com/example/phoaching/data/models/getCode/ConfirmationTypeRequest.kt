package com.example.phoaching.data.models.getCode

import kotlinx.serialization.SerialName

enum class ConfirmationTypeRequest {
    @SerialName("FAST_AUTH")
    FAST_AUTH,
    @SerialName("CONFIRMATION")
    CONFIRMATION
}
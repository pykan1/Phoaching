package com.example.phoaching.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class BaseDataResponse<T> (
    val success: Boolean?,
    val data: T?,
    val error: String?,
    @SerialName("error_number")
    val errorNumber: Int?
)
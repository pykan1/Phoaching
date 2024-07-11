package com.example.phoaching.data.models.checklist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class ChecklistResponse(
    @SerialName("id") val id: Int?,
    @SerialName("title") val title: String?,
    @SerialName("user_id") val userId: Int?,
    @SerialName("phoaching_id") val phoachingId: Int?
)
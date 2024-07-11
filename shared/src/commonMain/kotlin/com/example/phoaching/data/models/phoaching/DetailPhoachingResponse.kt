package com.example.phoaching.data.models.phoaching

import com.example.phoaching.data.models.checklist.ChecklistResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class DetailPhoachingResponse(
    @SerialName("id") val id: String?,
    @SerialName("title") val title: String?,
    @SerialName("created") val created: Long?,
    @SerialName("user_id") val userId: Int?,
    @SerialName("description") val description: String?,
    @SerialName("duration") val duration: Int?,
    @SerialName("days") val days: List<PhoachingDayResponse>?,
    @SerialName("failures_count") val failuresCount: Int?,
    @SerialName("checklist") val checklist: ChecklistResponse?,
    @SerialName("keywords") val keywords: String?,
    val author: AuthorResponse?
)
package com.example.phoaching.data.models.phoaching

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class UpdatePhoachingRequest(
    val title: String,
    val author: String,
    val description: String,
    val duration: Int,
    val days: List<PhoachingDayRequest>,
    @SerialName("failures_count")
    val failuresCount: Int,
    val checklistid: Int,
    val keywords: String,
)

@Serializable
class PhoachingDayRequest (
    val number: Int,
    val title: String,
)
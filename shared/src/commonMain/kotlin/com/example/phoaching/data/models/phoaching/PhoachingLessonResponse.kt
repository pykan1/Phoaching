package com.example.phoaching.data.models.phoaching

import kotlinx.serialization.Serializable


@Serializable
class PhoachingLessonResponse(
    val id: String?,
    val title: String?,
    val day: PhoachingDayResponse?,
    val number: Int?,
)
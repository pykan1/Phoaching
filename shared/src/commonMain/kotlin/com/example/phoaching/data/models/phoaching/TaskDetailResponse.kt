package com.example.phoaching.data.models.phoaching

import kotlinx.serialization.SerialName

class TaskDetailResponse(

    @SerialName("id") val id: String?,
    @SerialName("phoaching") val phoaching: PhoachingResponse?,
    @SerialName("day") val day: PhoachingDayResponse?,
    @SerialName("number") val number: Int?,
    @SerialName("title") val title: String?,
    @SerialName("content") val content: String?,
    @SerialName("scores") val scores: Int?,
    @SerialName("answer") val answer: String?,
    @SerialName("answer_type") val answerType: String?,
    @SerialName("is_insight_in_day") val isInsightInDay: Boolean?,
    @SerialName("is_insight_in_phoching") val isInsightInPhoching: Boolean?,
    @SerialName("text_before_answer") val textBeforeAnswer: String?,
    @SerialName("text_after_answer") val textAfterAnswer: String?,
    @SerialName("point_id") val pointId: Int?,
    @SerialName("created") val created: String?,
    @SerialName("updated") val updated: String?

)
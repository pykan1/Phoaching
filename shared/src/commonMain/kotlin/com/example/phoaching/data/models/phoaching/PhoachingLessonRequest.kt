package com.example.phoaching.data.models.phoaching

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class PhoachingLessonRequest(
    @SerialName("day_number") val dayNumber: Int?,
    @SerialName("number") val number: Int?,
    @SerialName("title") val title: String?,
    @SerialName("content") val content: String?,
    @SerialName("scores") val scores: Int?,
    @SerialName("answer_type") val answerType: String?,
    @SerialName("answer") val answer: String?,
    @SerialName("is_insight_in_day") val isInsightInDay: Boolean,
    @SerialName("is_insight_in_phoching") val isInsightInPhoching: Boolean,
    @SerialName("text_before_answer") val textBeforeAnswer: String?,
    @SerialName("text_after_answer") val textAfterAnswer: String?,
    @SerialName("point_id") val pointId: Int?
)
package com.example.phoaching.domain.models.phoaching

class PhoachingLessonDetailUI(
    val id: String,
    val phoaching: PhoachingUI,
    val day: PhoachingDayUI,
    val number: Int,
    val title: String,
    val content: String,
    val scores: Int,
    val answer: String,
    val answerType: String,
    val isInsightInDay: Boolean,
    val isInsightInPhoaching: Boolean,
    val textBeforeAnswer: String,
    val textAfterAnswer: String,
    val pointId: Int,
    val created: String,
    val updated: String
) {
    companion object {
        val Default = PhoachingLessonDetailUI(
            id = "",
            phoaching = PhoachingUI.Default,
            day = PhoachingDayUI.Default,
            number = 0,
            title = "",
            content = "",
            scores = 0,
            answer = "",
            answerType = "",
            isInsightInDay = false,
            isInsightInPhoaching = false,
            textBeforeAnswer = "",
            textAfterAnswer = "",
            pointId = 0,
            created = "",
            updated = ""
        )
    }
}
package com.example.phoaching.screens.foaching.newLesson

import com.example.phoaching.domain.models.phoaching.CriterionAnswer

data class NewLessonState(
    val title: String,
    val textOfLesson: String,
    val description: String,
    val selectDay: Int?,
    val taskText: String,
    val criterion: CriterionAnswer?,
    val keyPhrase: String,
    val pointsByTask: Int?,
    val selectCheckList: String,
    val urlImage: String,
    val listOfDays: List<Int>,
    val ourInsite: Boolean,
    val insiteOfDay: Boolean,
    val textBeforeAnswer: String,
    val textAfterAnswer: String,
) {
    companion object {
        val InitState = NewLessonState(
            "", "", "", null, "", null, "", null, "", "", emptyList(), false, false, "", ""
        )
    }
}
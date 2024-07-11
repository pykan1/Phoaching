package com.example.phoaching.domain.models.phoaching

import com.example.phoaching.domain.models.checklist.ChecklistUI

data class DetailPhoachingUI(
    val id: String,
    val title: String,
    val author: String,
    val created: Long,
    val userId: Int,
    val description: String,
    val duration: Int,
    val dayWithTitle: List<PhoachingDayUI>,
    val quantityFailTasksForFailSeminar: Int,
    val checklist: ChecklistUI,
    val keywords: String
) {
    companion object {
        val Default = DetailPhoachingUI(
            id = "",
            title = "",
            author = "",
            created = 0,
            userId = 0,
            description = "",
            duration = 0,
            dayWithTitle = emptyList(),
            quantityFailTasksForFailSeminar = 0,
            checklist = ChecklistUI.Default,
            keywords = ""
        )
    }
}
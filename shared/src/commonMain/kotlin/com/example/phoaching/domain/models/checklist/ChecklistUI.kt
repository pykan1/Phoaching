package com.example.phoaching.domain.models.checklist

data class ChecklistUI(
    val id: Int,
    val title: String,
    val userId: Int,
    val phoachingId: Int
) {
    companion object {
        val Default = ChecklistUI(
            0, "", 0, 0
        )
    }
}
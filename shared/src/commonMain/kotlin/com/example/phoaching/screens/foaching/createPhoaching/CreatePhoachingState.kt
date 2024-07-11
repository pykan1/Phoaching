package com.example.phoaching.screens.foaching.createPhoaching

import com.example.phoaching.domain.models.phoaching.PhoachingDayUI

data class CreatePhoachingState(
    val id: String?,
    val title: String,
    val description: String,
    val author: String,
    val wrongUntilRepeat: Int?,
    val duration: Int?,
    val days: List<PhoachingDayUI>,
    val checkListId: Int
) {
    companion object {
        val InitState = CreatePhoachingState(
            null,"", "", "", null, null, emptyList(), 0
        )
    }
}
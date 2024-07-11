package com.example.phoaching.data.mapper

import com.example.phoaching.data.models.checklist.ChecklistResponse
import com.example.phoaching.domain.models.checklist.ChecklistUI
import com.example.phoaching.ext.orEmpty

fun ChecklistResponse.toUI(): ChecklistUI {
    return ChecklistUI(
        id = id.orEmpty(),
        title = title.orEmpty(),
        userId = userId.orEmpty(),
        phoachingId = phoachingId.orEmpty()
    )
}
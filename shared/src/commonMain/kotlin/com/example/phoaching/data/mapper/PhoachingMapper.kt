package com.example.phoaching.data.mapper

import com.example.phoaching.data.models.phoaching.DetailPhoachingResponse
import com.example.phoaching.data.models.phoaching.PhoachingDayResponse
import com.example.phoaching.data.models.phoaching.PhoachingResponse
import com.example.phoaching.domain.models.checklist.ChecklistUI
import com.example.phoaching.domain.models.phoaching.DetailPhoachingUI
import com.example.phoaching.domain.models.phoaching.PhoachingDayUI
import com.example.phoaching.domain.models.phoaching.PhoachingUI
import com.example.phoaching.ext.orEmpty

fun PhoachingResponse.toUI(): PhoachingUI {
    return PhoachingUI(
        id = id.orEmpty(),
        title = title.orEmpty(),
        author = "${author?.lastName.orEmpty()} ${author?.firstName.orEmpty()}"
    )
}


fun DetailPhoachingResponse.toUI(): DetailPhoachingUI {
    return DetailPhoachingUI(
        id = id.orEmpty(),
        userId = userId.orEmpty(),
        title = title.orEmpty(),
        created = created?: 0,
        description = description.orEmpty(),
        duration = duration.orEmpty(),
        dayWithTitle = days?.map { it.toUI() }.orEmpty(),
        quantityFailTasksForFailSeminar = failuresCount.orEmpty(),
        checklist = checklist?.toUI() ?: ChecklistUI.Default,
        keywords = keywords.orEmpty(),
        author = "${author?.lastName.orEmpty()} ${author?.firstName.orEmpty()}"
    )
}

fun PhoachingDayResponse.toUI(): PhoachingDayUI {
    return PhoachingDayUI(
        number.orEmpty(), title.orEmpty()
    )
}
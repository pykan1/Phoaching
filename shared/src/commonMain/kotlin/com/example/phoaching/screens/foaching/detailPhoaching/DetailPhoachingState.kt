package com.example.phoaching.screens.foaching.detailPhoaching

import com.example.phoaching.domain.models.phoaching.DetailPhoachingUI
import com.example.phoaching.domain.models.phoaching.PhoachingLessonUI

data class DetailPhoachingState(
    val phoachingUI: DetailPhoachingUI,
    val tasks: List<PhoachingLessonUI>
) {
    companion object {
        val InitState = DetailPhoachingState(DetailPhoachingUI.Default, emptyList())
    }
}
package com.example.phoaching.screens.foaching.detailLessonScreen

import com.example.phoaching.domain.models.phoaching.PhoachingLessonDetailUI

data class DetailLessonState(
    val lessonDetailUI: PhoachingLessonDetailUI
) {
    companion object {
        val InitState = DetailLessonState(
            lessonDetailUI = PhoachingLessonDetailUI.Default
        )
    }
}
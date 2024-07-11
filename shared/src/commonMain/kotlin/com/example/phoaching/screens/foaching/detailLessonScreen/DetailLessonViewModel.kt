package com.example.phoaching.screens.foaching.detailLessonScreen

import com.example.phoaching.domain.repository.PhoachingRepository
import com.example.phoaching.platform.BaseScreenModel
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent

internal class DetailLessonViewModel :
    BaseScreenModel<DetailLessonState, DetailLessonEvent>(DetailLessonState.InitState) {

    private val phoachingRepository: PhoachingRepository by inject()

    fun loadLesson(lessonId: String, phoachingId: String) = intent {
        launchOperation(
            operation = {
                phoachingRepository.getTaskById(lessonId, phoachingId)
            },
            success = {
                reduceLocal {
                    state.copy(
                        lessonDetailUI = it
                    )
                }
            }
        )
    }

}
package com.example.phoaching.screens.foaching.createPhoaching

import com.example.phoaching.domain.models.phoaching.PhoachingDayUI
import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.domain.usecases.phoaching.CreatePhoachingUseCase
import com.example.phoaching.domain.usecases.phoaching.GetDetailPhoachingUseCase
import com.example.phoaching.domain.usecases.phoaching.UpdatePhoachingUseCase
import com.example.phoaching.ext.orEmpty
import com.example.phoaching.platform.BaseScreenModel
import org.koin.core.component.inject
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

@OptIn(OrbitExperimental::class)
internal class CreatePhoachingViewModel :
    BaseScreenModel<CreatePhoachingState, CreatePhoachingEvent>(
        CreatePhoachingState.InitState
    ) {

    private val phoachingDetailPhoachingUseCase: GetDetailPhoachingUseCase by inject()
    private val createPhoachingUseCase: CreatePhoachingUseCase by inject()
    private val updatePhoachingUseCase: UpdatePhoachingUseCase by inject()

    fun changeTitle(title: String) = blockingIntent {
        reduce {
            state.copy(
                title = title
            )
        }
    }

    fun loadPhoachingId(id: String?, tab: PhoachingTab) = intent {
        if (id != null) {
            launchOperation(
                operation = {
                    phoachingDetailPhoachingUseCase.execute(
                        GetDetailPhoachingUseCase.Params(id = id, tab = tab),
                        it
                    )
                },
                success = {
                    reduceLocal {
                        state.copy(
                            id = id,
                            title = it.title,
                            description = it.description,
                            author = it.author,
                            wrongUntilRepeat = it.quantityFailTasksForFailSeminar,
                            duration = it.duration,
                            days = it.dayWithTitle,
                            checkListId = it.checklist.id
                        )
                    }
                }
            )
        }
    }

    fun changeDescription(description: String) = blockingIntent {
        reduce {
            state.copy(
                description = description
            )
        }
    }

    fun changeAuthor(author: String) = blockingIntent {
        reduce {
            state.copy(
                author = author
            )
        }
    }

    fun changeWrongUtilRepeat(wrong: String) = blockingIntent {
        reduce {
            state.copy(
                wrongUntilRepeat = wrong.filter { it.isDigit() }.take(2).toIntOrNull().orEmpty()
            )
        }
    }

    fun changeDuration(duration: String) = blockingIntent {
        val newDuration = duration.filter { it.isDigit() }.take(2).toIntOrNull().orEmpty()

        reduce {
            state.copy(
                duration = newDuration, days = (1 .. newDuration).map { PhoachingDayUI(number = it, title = "") }
            )
        }
    }

    fun changeDayTitle(dayNumber: Int, title: String) = blockingIntent {
        reduce {
            state.copy(
                days = state.days.map { if (it.number == dayNumber) it.copy(title = title) else it }
            )
        }
    }

    fun create() = intent {
        launchOperation(
            operation = {
                if (state.id == null) {
                    createPhoachingUseCase.execute(
                        CreatePhoachingUseCase.Params(
                            title = state.title,
                            author = state.author,
                            description = state.description,
                            duration = state.duration.orEmpty(),
                            dayWithTitle = state.days,
                            quantityFailTasksForFailSeminar = state.wrongUntilRepeat.orEmpty(),
                            keywords = "", //todo
                            checklistid = state.checkListId,
                        ), it
                    )
                } else {
                    updatePhoachingUseCase.execute(
                        UpdatePhoachingUseCase.Params(
                            id = state.id.orEmpty(),
                            title = state.title,
                            author = state.author,
                            description = state.description,
                            duration = state.duration.orEmpty(),
                            dayWithTitle = state.days,
                            quantityFailTasksForFailSeminar = state.wrongUntilRepeat.orEmpty(),
                            keywords = "", //todo
                            checklistid = state.checkListId,
                        ), it
                    )
                }

            },
            success = {
                postSideEffectLocal(CreatePhoachingEvent.Success)
            }
        )
    }


}
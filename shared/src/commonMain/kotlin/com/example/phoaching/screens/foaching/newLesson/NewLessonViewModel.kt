package com.example.phoaching.screens.foaching.newLesson

import com.example.phoaching.data.repository.PhoachingRepositoryImpl
import com.example.phoaching.domain.models.phoaching.CriterionAnswer
import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.domain.usecases.phoaching.GetDetailPhoachingUseCase
import com.example.phoaching.ext.orEmpty
import com.example.phoaching.platform.BaseScreenModel
import org.koin.core.component.inject
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

@OptIn(OrbitExperimental::class)
internal class NewLessonViewModel :
    BaseScreenModel<NewLessonState, NewLessonEvent>(NewLessonState.InitState) {

    private val getPhoachingById: GetDetailPhoachingUseCase by inject()
    private val phoachingRepositoryImpl: PhoachingRepositoryImpl by inject()

    fun loadData(lessonId: String?, phoachingId: String, tab: PhoachingTab) = intent {
        launchOperation(
            operation = {
                getPhoachingById.execute(
                    GetDetailPhoachingUseCase.Params(phoachingId, tab = tab),
                    it
                )
            },
            success = {
                println("it.dayWithTitle - ${it.dayWithTitle}")
                reduceLocal {
                    state.copy(
                        listOfDays = it.dayWithTitle.map { it.number },
                    )
                }
            }
        )
    }


    fun changeTitle(title: String) = blockingIntent {
        reduce {
            state.copy(title = title)
        }
    }

    fun changeTaskText(it: String) = blockingIntent {
        reduce {
            state.copy(
                taskText = it
            )
        }
    }


    fun changeDayPhoaching(it: Int) = blockingIntent {
        reduce {
            state.copy(selectDay = it)
        }
    }

    fun changeSelectChecklist(it: String) = blockingIntent {
        reduce {
            state.copy(
                selectCheckList = it
            )
        }
    }

    fun changeCriterion(it: CriterionAnswer?) = intent {
        reduce {
            state.copy(
                criterion = it,
            )
        }
    }

    fun changeKeyPhrase(it: String) = blockingIntent {
        reduce {
            state.copy(
                keyPhrase = it
            )
        }
    }

    fun changeTextAfterAnswer(it: String) = blockingIntent {
        reduce {
            state.copy(textAfterAnswer = it)
        }
    }

    fun changeTextBeforeAnswer(it: String) = blockingIntent {
        reduce {
            state.copy(textBeforeAnswer = it)
        }
    }

    fun changeUrlImage(url: String) = blockingIntent {
        reduce {
            state.copy(
                urlImage = url
            )
        }
    }

    fun changePointByTask(it: String) = blockingIntent {
        reduce {
            state.copy(pointsByTask = it.filter { it.isDigit() }.take(2).toIntOrNull().orEmpty())
        }
    }

    fun changeTextOfLesson(it: String) = blockingIntent {
        reduce {
            state.copy(
                textOfLesson = it
            )
        }
    }

    fun changeInsiteOfDay() = intent {
        reduce {
            state.copy(insiteOfDay = !state.insiteOfDay)
        }
    }

    fun changeOurInsite() = intent {
        reduce {
            state.copy(ourInsite = !state.ourInsite)
        }
    }

    fun save(lessonId: String?, phoachingId: String) = intent {
        launchOperation(
            operation = {
                lessonId?.let {
                    phoachingRepositoryImpl.updatePhoachingLesson(
                        taskId = it,
                        phoachingId = phoachingId,
                        dayNumber = state.selectDay.orEmpty(1),
                        number = 1,
                        title = state.title,
                        content = state.textOfLesson,
                        scores = state.pointsByTask.orEmpty(1),
                        answerType = state.criterion?.name.orEmpty(),
                        textAfterAnswer = state.textAfterAnswer,
                        textBeforeAnswer = state.textBeforeAnswer,
                        pointId = state.pointsByTask.orEmpty(0),
                        isInsightInDay = state.insiteOfDay,
                        isInsightInPhoching = state.ourInsite,
                        answer = state.keyPhrase,
                    )
                } ?: phoachingRepositoryImpl.createPhoachingLesson(
                    phoachingId = phoachingId,
                    dayNumber = state.selectDay.orEmpty(1),
                    number = 1,
                    title = state.title,
                    content = state.textOfLesson,
                    scores = state.pointsByTask.orEmpty(1),
                    answerType = state.criterion?.name.orEmpty(),
                    textAfterAnswer = state.textAfterAnswer,
                    textBeforeAnswer = state.textBeforeAnswer,
                    pointId = state.pointsByTask.orEmpty(0),
                    isInsightInDay = state.insiteOfDay,
                    isInsightInPhoching = state.ourInsite,
                    answer = state.keyPhrase,
                )
            },
            success = {
                postSideEffectLocal(NewLessonEvent.Success)
            }
        )
    }


}
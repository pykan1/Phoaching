package com.example.phoaching.data.repository

import com.example.phoaching.data.api.PhoachingApi
import com.example.phoaching.data.mapper.toUI
import com.example.phoaching.data.models.phoaching.PhoachingDayRequest
import com.example.phoaching.data.models.phoaching.PhoachingLessonRequest
import com.example.phoaching.data.models.phoaching.UpdatePhoachingRequest
import com.example.phoaching.domain.models.phoaching.DetailPhoachingUI
import com.example.phoaching.domain.models.phoaching.PhoachingDayUI
import com.example.phoaching.domain.models.phoaching.PhoachingLessonDetailUI
import com.example.phoaching.domain.models.phoaching.PhoachingLessonUI
import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.domain.models.phoaching.PhoachingTab.Author
import com.example.phoaching.domain.models.phoaching.PhoachingTab.My
import com.example.phoaching.domain.models.phoaching.PhoachingUI
import com.example.phoaching.domain.repository.PhoachingRepository
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import com.example.phoaching.platform.apiCall

class PhoachingRepositoryImpl(private val phoachingApi: PhoachingApi) : PhoachingRepository {
    override suspend fun getPhoachings(tab: PhoachingTab): Either<Failure, List<PhoachingUI>> {
        return apiCall(
            call = {
                when (tab) {
                    My -> phoachingApi.getPhoachings()
                    Author -> phoachingApi.getAuthorPhoachings()
                }
            },
            mapResponse = {
                it.data?.map { it.toUI() }.orEmpty()
            }
        )
    }

    override suspend fun getPhoachingById(
        id: String,
        tab: PhoachingTab
    ): Either<Failure, DetailPhoachingUI> {
        return apiCall(
            call = {
                when (tab) {
                    My -> phoachingApi.getPhoachingById(id)
                    Author -> phoachingApi.getAuthorPhoachingById(id)
                }

            },
            mapResponse = {
                it.data?.toUI() ?: DetailPhoachingUI.Default
            }
        )
    }

    override suspend fun updatePhoaching(
        id: String,
        title: String,
        author: String,
        description: String,
        duration: Int,
        dayWithTitle: List<PhoachingDayUI>,
        quantityFailTasksForFailSeminar: Int,
        checklistid: Int,
        keywords: String,
    ): Either<Failure, Unit> {
        return apiCall(
            call = {
                phoachingApi.updatePhoaching(
                    id = id, request = UpdatePhoachingRequest(
                        title,
                        author,
                        description,
                        duration,
                        dayWithTitle.map {
                            PhoachingDayRequest(
                                number = it.number,
                                title = it.title
                            )
                        },
                        quantityFailTasksForFailSeminar,
                        checklistid,
                        keywords
                    )
                )
            },
        )
    }

    override suspend fun deletePhoaching(id: String): Either<Failure, Unit> {
        return apiCall(
            call = {
                phoachingApi.deletePhoaching(id)
            }
        )
    }

    override suspend fun createPhoaching(
        title: String,
        author: String,
        description: String,
        duration: Int,
        dayWithTitle: List<PhoachingDayUI>,
        quantityFailTasksForFailSeminar: Int,
        checklistid: Int,
        keywords: String,
    ): Either<Failure, Unit> {
        return apiCall(
            call = {
                phoachingApi.createPhoaching(
                    request = UpdatePhoachingRequest(
                        title,
                        author,
                        description,
                        duration,
                        dayWithTitle.map {
                            PhoachingDayRequest(
                                number = it.number,
                                title = it.title
                            )
                        },
                        quantityFailTasksForFailSeminar,
                        checklistid,
                        keywords
                    )
                )
            }
        )
    }

    override suspend fun linkPhoaching(id: String): Either<Failure, String> {
        return apiCall(
            call = {
                phoachingApi.linkPhoaching(id)
            },
            mapResponse = {
                it.data?.link.orEmpty()
            }
        )
    }

    override suspend fun copyPhoaching(id: String): Either<Failure, Unit> {
        return apiCall(
            call = {
                phoachingApi.copyPhoaching(id)
            }
        )
    }

    override suspend fun getPhoachingTasks(phoachingId: String): Either<Failure, List<PhoachingLessonUI>> {
        return apiCall(
            call = {
                phoachingApi.getPhoachingTasks(phoachingId)
            },
            mapResponse = { it.data?.map { it.toUI() }.orEmpty() }
        )
    }

    override suspend fun getTaskById(
        phoachingId: String,
        taskId: String
    ): Either<Failure, PhoachingLessonDetailUI> {
        return apiCall(
            call = {
                phoachingApi.getTaskById(phoachingId, taskId)
            },
            mapResponse = {
                it.data?.toUI() ?: PhoachingLessonDetailUI.Default
            }
        )
    }

    override suspend fun deleteTask(phoachingId: String, taskId: String): Either<Failure, Unit> {
        return apiCall(
            call = {
                phoachingApi.deleteTask(phoachingId, taskId)
            }
        )
    }

    override suspend fun createPhoachingLesson(
        phoachingId: String,
        dayNumber: Int,
        number: Int,
        title: String,
        content: String,
        scores: Int,
        answerType: String,
        answer: String, isInsightInDay: Boolean,
        isInsightInPhoching: Boolean,
        textBeforeAnswer: String,
        textAfterAnswer: String,
        pointId: Int
    ): Either<Failure, Unit> {
        return apiCall(
            call = {
                phoachingApi.createPhoachingLesson(
                    phoachingId = phoachingId,
                    phoachingLessonRequest = PhoachingLessonRequest(
                        dayNumber = dayNumber,
                        number = number,
                        title = title,
                        content = content,
                        scores = scores,
                        answerType = answerType,
                        answer = answer,
                        isInsightInDay = isInsightInDay,
                        isInsightInPhoching = isInsightInPhoching,
                        textBeforeAnswer = textBeforeAnswer,
                        textAfterAnswer = textAfterAnswer,
                        pointId = pointId
                    )
                )
            }
        )
    }

    override suspend fun updatePhoachingLesson(
        taskId: String,
        phoachingId: String,
        dayNumber: Int,
        number: Int,
        title: String,
        content: String,
        scores: Int,
        answerType: String,
        answer: String, isInsightInDay: Boolean,
        isInsightInPhoching: Boolean,
        textBeforeAnswer: String,
        textAfterAnswer: String,
        pointId: Int
    ): Either<Failure, Unit> {
        return apiCall(
            call = {
                phoachingApi.updatePhoachingLesson(
                    taskId = taskId,
                    phoachingId = phoachingId,
                    phoachingLessonRequest = PhoachingLessonRequest(
                        dayNumber = dayNumber,
                        number = number,
                        title = title,
                        content = content,
                        scores = scores,
                        answerType = answerType,
                        answer = answer,
                        isInsightInDay = isInsightInDay,
                        isInsightInPhoching = isInsightInPhoching,
                        textBeforeAnswer = textBeforeAnswer,
                        textAfterAnswer = textAfterAnswer,
                        pointId = pointId
                    )
                )
            }
        )
    }

    override suspend fun phoachingPurchase(phoachingId: String): Either<Failure, Unit> {
        return apiCall(
            call = {
                phoachingApi.phoachingPurchase(phoachingId)
            }
        )
    }

}
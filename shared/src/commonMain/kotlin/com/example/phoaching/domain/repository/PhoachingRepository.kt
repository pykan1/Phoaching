package com.example.phoaching.domain.repository

import com.example.phoaching.domain.models.phoaching.DetailPhoachingUI
import com.example.phoaching.domain.models.phoaching.PhoachingDayUI
import com.example.phoaching.domain.models.phoaching.PhoachingLessonDetailUI
import com.example.phoaching.domain.models.phoaching.PhoachingLessonUI
import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.domain.models.phoaching.PhoachingUI
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import de.jensklingenberg.ktorfit.http.Path

interface PhoachingRepository {
    suspend fun getPhoachings(tab: PhoachingTab): Either<Failure, List<PhoachingUI>>

    suspend fun getPhoachingById(id: String, tab: PhoachingTab): Either<Failure, DetailPhoachingUI>

    suspend fun updatePhoaching(
        id: String,
        title: String,
        author: String,
        description: String,
        duration: Int,
        dayWithTitle: List<PhoachingDayUI>,
        quantityFailTasksForFailSeminar: Int,
        checklistid: Int,
        keywords: String,
    ): Either<Failure, Unit>

    suspend fun deletePhoaching(id: String): Either<Failure, Unit>

    suspend fun createPhoaching(
        title: String,
        author: String,
        description: String,
        duration: Int,
        dayWithTitle: List<PhoachingDayUI>,
        quantityFailTasksForFailSeminar: Int,
        checklistid: Int,
        keywords: String,
    ): Either<Failure, Unit>

    suspend fun linkPhoaching(id: String): Either<Failure, String>

    suspend fun copyPhoaching(id: String): Either<Failure, Unit>


    suspend fun getPhoachingTasks(phoachingId: String): Either<Failure, List<PhoachingLessonUI>>

    suspend fun getTaskById(
     phoachingId: String,
       taskId: String
    ): Either<Failure, PhoachingLessonDetailUI>

    suspend fun deleteTask(
      phoachingId: String,
       taskId: String
    ): Either<Failure, Unit>


    suspend fun createPhoachingLesson(
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
    ): Either<Failure, Unit>

    suspend fun updatePhoachingLesson(
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
    ): Either<Failure, Unit>

    suspend fun phoachingPurchase(@Path("id") phoachingId: String): Either<Failure, Unit>

}
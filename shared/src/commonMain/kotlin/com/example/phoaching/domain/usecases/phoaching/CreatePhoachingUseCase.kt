package com.example.phoaching.domain.usecases.phoaching

import com.example.phoaching.domain.models.phoaching.PhoachingDayUI
import com.example.phoaching.domain.repository.PhoachingRepository
import com.example.phoaching.platform.BaseUseCase
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import kotlinx.coroutines.CoroutineScope

class CreatePhoachingUseCase(private val phoachingRepository: PhoachingRepository) :
    BaseUseCase<CreatePhoachingUseCase.Params, Unit>() {

    class Params(
        val title: String,
        val author: String,
        val description: String,
        val duration: Int,
        val dayWithTitle: List<PhoachingDayUI>,
        val quantityFailTasksForFailSeminar: Int,
        val checklistid: Int,
        val keywords: String,
    )

    override suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, Unit> {
        return phoachingRepository.createPhoaching(
            title = params.title,
            author = params.author,
            description = params.description,
            duration = params.duration,
            dayWithTitle = params.dayWithTitle,
            quantityFailTasksForFailSeminar = params.quantityFailTasksForFailSeminar,
            checklistid = params.checklistid,
            keywords = params.keywords,
        )
    }
}
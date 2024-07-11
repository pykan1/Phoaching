package com.example.phoaching.domain.usecases.phoaching

import com.example.phoaching.domain.models.phoaching.DetailPhoachingUI
import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.domain.repository.PhoachingRepository
import com.example.phoaching.platform.BaseUseCase
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import kotlinx.coroutines.CoroutineScope

class GetDetailPhoachingUseCase(private val phoachingRepository: PhoachingRepository) :
    BaseUseCase<GetDetailPhoachingUseCase.Params, DetailPhoachingUI>() {
    class Params(val id: String, val tab: PhoachingTab)

    override suspend fun execute(
        params: Params,
        scope: CoroutineScope
    ): Either<Failure, DetailPhoachingUI> {
        return phoachingRepository.getPhoachingById(params.id, params.tab)
    }
}
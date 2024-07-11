package com.example.phoaching.domain.usecases.phoaching

import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.domain.models.phoaching.PhoachingUI
import com.example.phoaching.domain.repository.PhoachingRepository
import com.example.phoaching.platform.BaseUseCase
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import kotlinx.coroutines.CoroutineScope

class GetPhoachingsUseCase(private val phoachingRepository: PhoachingRepository) :
    BaseUseCase<GetPhoachingsUseCase.Params, List<PhoachingUI>>() {

    class Params(val tab: PhoachingTab)

    override suspend fun execute(
        params: Params,
        scope: CoroutineScope
    ): Either<Failure, List<PhoachingUI>> {
        return phoachingRepository.getPhoachings(params.tab)
    }

}
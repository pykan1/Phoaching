package com.example.phoaching.domain.usecases.phoaching

import com.example.phoaching.domain.repository.PhoachingRepository
import com.example.phoaching.platform.BaseUseCase
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import kotlinx.coroutines.CoroutineScope

class UrlPhoachingUseCase(private val phoachingRepository: PhoachingRepository): BaseUseCase<UrlPhoachingUseCase.Params, String>() {

    class Params(val id: String)

    override suspend fun execute(
        params: Params,
        scope: CoroutineScope
    ): Either<Failure, String> {
        return phoachingRepository.linkPhoaching(params.id)
    }
}
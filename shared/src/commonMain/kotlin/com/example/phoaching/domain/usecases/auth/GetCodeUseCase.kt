package com.example.phoaching.domain.usecases.auth

import com.example.phoaching.domain.models.ConfirmationType
import com.example.phoaching.domain.models.auth.CodeUI
import com.example.phoaching.domain.repository.AuthRepository
import com.example.phoaching.platform.BaseUseCase
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import kotlinx.coroutines.CoroutineScope

class GetCodeUseCase(private val authRepository: AuthRepository): BaseUseCase<GetCodeUseCase.Params, CodeUI>() {

    class Params(val target: String, val confirmationType: ConfirmationType?)

    override suspend fun execute(
        params: Params,
        scope: CoroutineScope
    ): Either<Failure, CodeUI> {
        return authRepository.getCode(target = params.target, confirmationType = params.confirmationType)
    }
}
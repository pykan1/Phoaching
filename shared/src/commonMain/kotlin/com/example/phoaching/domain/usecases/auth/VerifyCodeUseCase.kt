package com.example.phoaching.domain.usecases.auth

import com.example.phoaching.domain.models.ConfirmationType
import com.example.phoaching.domain.repository.AuthRepository
import com.example.phoaching.platform.BaseUseCase
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import kotlinx.coroutines.CoroutineScope

class VerifyCodeUseCase(private val authRepository: AuthRepository) :
    BaseUseCase<VerifyCodeUseCase.Params, Unit>() {

    class Params(val code: String, val id: String)

    override suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, Unit> {
        return authRepository.verifyCode(params.id, params.code)
    }

}
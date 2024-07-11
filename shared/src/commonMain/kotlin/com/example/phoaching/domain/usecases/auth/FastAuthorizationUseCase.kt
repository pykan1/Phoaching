package com.example.phoaching.domain.usecases.auth

import kotlinx.coroutines.CoroutineScope
import com.example.phoaching.domain.repository.AuthRepository
import com.example.phoaching.platform.BaseUseCase
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure

class FastAuthorizationUseCase(private val authRepository: AuthRepository): BaseUseCase<FastAuthorizationUseCase.Params, Unit>() {

    class Params(val id: String, val code: String)

    override suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, Unit> {
        return authRepository.fastAuthorization(params.id, params.code)
    }
}
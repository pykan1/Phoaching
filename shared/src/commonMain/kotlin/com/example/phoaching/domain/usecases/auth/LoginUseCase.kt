package com.example.phoaching.domain.usecases.auth

import kotlinx.coroutines.CoroutineScope
import com.example.phoaching.domain.repository.AuthRepository
import com.example.phoaching.platform.BaseUseCase
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure

class LoginUseCase(private val authRepository: AuthRepository): BaseUseCase<LoginUseCase.Params, Unit>() {
    class Params(val username: String, val password: String)

    override suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, Unit> {
        return authRepository.login(params.username, params.password)
    }
}
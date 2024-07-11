package com.example.phoaching.domain.usecases.auth

import com.example.phoaching.domain.repository.AuthRepository
import com.example.phoaching.platform.BaseUseCase
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import kotlinx.coroutines.CoroutineScope

class RegisterUseCase(private val authRepository: AuthRepository) :
    BaseUseCase<RegisterUseCase.Params, Unit>() {

    class Params(
        val username: String,
        val firstname: String,
        val lastname: String,
        val email: String,
        val password: String,
        val repeatPassword: String,
        val tel: String,
        val city: String = "",
        val county: String = "",
    )

    override suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, Unit> {
        return authRepository.register(
            username = params.username,
            firstname = params.firstname,
            lastname = params.lastname,
            email = params.email,
            password = params.password,
            repeatPassword = params.repeatPassword,
            tel = params.tel,
            city = params.city,
            county = params.county
        )
    }

}
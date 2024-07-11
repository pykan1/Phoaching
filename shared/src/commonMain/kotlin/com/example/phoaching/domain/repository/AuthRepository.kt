package com.example.phoaching.domain.repository

import com.example.phoaching.domain.models.ConfirmationType
import com.example.phoaching.domain.models.auth.CodeUI
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure

interface AuthRepository {

    suspend fun login(username: String, password: String): Either<Failure, Unit>

    suspend fun getCode(target: String, confirmationType: ConfirmationType?): Either<Failure, CodeUI>

    suspend fun fastAuthorization(id: String, code: String): Either<Failure, Unit>

    suspend fun register(
        username: String,
        firstname: String,
        lastname: String,
        email: String,
        password: String,
        repeatPassword: String,
        tel: String,
        city: String,
        county: String
    ): Either<Failure, Unit>

    suspend fun verifyCode(
        id: String,
        code: String,
    ): Either<Failure, Unit>

}
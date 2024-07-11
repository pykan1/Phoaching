package com.example.phoaching.data.repository

import com.example.phoaching.data.api.AuthApi
import com.example.phoaching.data.mapper.toUI
import com.example.phoaching.data.models.fastAuth.CodeRequest
import com.example.phoaching.data.models.getCode.ConfirmationTypeRequest
import com.example.phoaching.data.models.getCode.GetCodeRequest
import com.example.phoaching.data.models.login.LoginRequest
import com.example.phoaching.data.models.register.RegisterRequest
import com.example.phoaching.domain.manager.AuthManager
import com.example.phoaching.domain.models.ConfirmationType
import com.example.phoaching.domain.models.auth.CodeUI
import com.example.phoaching.domain.repository.AuthRepository
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import com.example.phoaching.platform.apiCall

class AuthRepositoryImpl(private val authApi: AuthApi, private val authManager: AuthManager) :
    AuthRepository {
    override suspend fun login(username: String, password: String): Either<Failure, Unit> {
        return apiCall(
            call = {
                authApi.login(LoginRequest(username, password))
            },
            mapResponse = {
                authManager.token = it.data?.token.orEmpty()
                authManager.profile = it.data?.profile?.toUI()
                authManager.isAuthorized = true
            }
        )
    }

    override suspend fun getCode(target: String, confirmationType: ConfirmationType?): Either<Failure, CodeUI> {
        return apiCall(
            call = {
                authApi.getCode(
                    GetCodeRequest(
                        target = target,
                        confirmationType = ConfirmationTypeRequest.entries.find { it.name == confirmationType?.name }
                    )
                )
            },
            mapResponse = {
                it.data?.toUI() ?: CodeUI.Default
            }
        )
    }

    override suspend fun fastAuthorization(
        id: String,
        code: String
    ): Either<Failure, Unit> {
        return apiCall(
            call = {
                authApi.fastAuthorization(
                    CodeRequest(
                        id = id,
                        code = code,
                    )
                )
            },
            mapResponse = {
                authManager.token = it.data?.token.orEmpty()
                authManager.profile = it.data?.profile?.toUI()
                authManager.isAuthorized = true
            }
        )
    }

    override suspend fun register(
        username: String,
        firstname: String,
        lastname: String,
        email: String,
        password: String,
        repeatPassword: String,
        tel: String,
        city: String,
        county: String
    ): Either<Failure, Unit> {
        return apiCall(
            call = {
                authApi.register(
                    request = RegisterRequest(
                        userName = username,
                        firstName = firstname,
                        lastName = lastname,
                        email, password, repeatPassword, tel, city
                    )
                )
            },
            mapResponse = {
                authManager.token = it.data?.token.orEmpty()
            }
        )
    }

    override suspend fun verifyCode(
        id: String,
        code: String,
    ): Either<Failure, Unit> {
        return apiCall(
            call = {
                authApi.verifyCode(
                    body = CodeRequest(
                        id = id,
                        code = code,
                       )
                )
            }
        )
    }


}
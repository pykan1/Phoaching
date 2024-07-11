package com.example.phoaching.data.api

import com.example.phoaching.data.models.BaseDataResponse
import com.example.phoaching.data.models.fastAuth.AuthorizationResponse
import com.example.phoaching.data.models.fastAuth.CodeRequest
import com.example.phoaching.data.models.getCode.GetCodeRequest
import com.example.phoaching.data.models.getCode.GetCodeResponse
import com.example.phoaching.data.models.login.LoginRequest
import com.example.phoaching.data.models.register.RegisterRequest
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST

interface AuthApi {
    @POST("api/v2/auth/log-in")
    suspend fun login(@Body request: LoginRequest): BaseDataResponse<AuthorizationResponse>

    @POST("api/v2/auth/get-code")
    suspend fun getCode(@Body request: GetCodeRequest): BaseDataResponse<GetCodeResponse>

    @POST("api/v2/auth/fast-authorization")
    suspend fun fastAuthorization(@Body request: CodeRequest): BaseDataResponse<AuthorizationResponse>

    @POST("api/v2/auth/registration")
    suspend fun register(@Body request: RegisterRequest): BaseDataResponse<AuthorizationResponse>


    @POST("api/v2/auth/verify-code")
    suspend fun verifyCode(@Body body: CodeRequest): BaseDataResponse<Unit>



}
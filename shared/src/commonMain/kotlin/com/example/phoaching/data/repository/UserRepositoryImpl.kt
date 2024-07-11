package com.example.phoaching.data.repository

import com.example.phoaching.data.api.UserApi
import com.example.phoaching.domain.repository.UserRepository
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import com.example.phoaching.platform.Form
import com.example.phoaching.platform.MultipartManager
import com.example.phoaching.platform.apiCall

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val multipartManager: MultipartManager,
) : UserRepository {

    override suspend fun changeAvatar(url: String): Either<Failure, Unit> {
        return apiCall(
            call = {
                userApi.changeAvatar(body = multipartManager.createMultipart(Form.FormFile("avatar", url)))
            }
        )
    }
}
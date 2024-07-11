package com.example.phoaching.domain.repository

import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure

interface UserRepository {

    suspend fun changeAvatar(url: String): Either<Failure, Unit>

}
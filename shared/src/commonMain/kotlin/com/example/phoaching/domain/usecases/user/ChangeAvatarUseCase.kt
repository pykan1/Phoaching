package com.example.phoaching.domain.usecases.user

import com.example.phoaching.domain.repository.UserRepository
import com.example.phoaching.platform.BaseUseCase
import com.example.phoaching.platform.Either
import com.example.phoaching.platform.Failure
import kotlinx.coroutines.CoroutineScope

class ChangeAvatarUseCase(private val userRepository: UserRepository): BaseUseCase<ChangeAvatarUseCase.Params, Unit>() {

    class Params(val avatar: String)

    override suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, Unit> {
        return userRepository.changeAvatar(params.avatar)
    }
}

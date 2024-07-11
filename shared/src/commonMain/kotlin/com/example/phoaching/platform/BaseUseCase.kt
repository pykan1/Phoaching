package com.example.phoaching.platform

import kotlinx.coroutines.*

/**
 * Базовый UseCase для бизнес логики
 */
abstract class BaseUseCase<in Params, out Type> where Type : Any {

    abstract suspend fun execute(params: Params, scope: CoroutineScope): Either<Failure, Type>
    open suspend operator fun invoke(
        scope: CoroutineScope,
        params: Params,
    ): Either<Failure, Type> {
        val deferred = scope.async { execute(params, this) }
        return withContext(scope.coroutineContext) {
            try {
                deferred.await()
            } catch (e: Exception) {
                Either.Left(Failure.Message(e.message.orEmpty()))
            }
        }
    }
}
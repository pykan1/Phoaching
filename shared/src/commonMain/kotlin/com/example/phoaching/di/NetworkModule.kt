package com.example.phoaching.di

import com.example.phoaching.domain.manager.AuthManager
import com.example.phoaching.platform.Failure
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRedirect
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.content.TextContent
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

const val BASE_URL = "http://90.156.205.157:8090/test/"

internal val networkModule = module {
    factoryOf(::provideJson)
    factoryOf(::provideHttpClient)
    factory { provideKtorHttpClient(get(), BASE_URL, get()) }

}

@OptIn(ExperimentalSerializationApi::class)
private fun provideJson(): Json {
    return Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
        encodeDefaults = true
        prettyPrint = true
    }
}

private const val TIME_OUT = 60000L

private fun provideHttpClient(
    json: Json,
    authManager: AuthManager,
) = HttpClient {


    install(HttpRedirect) {
        checkHttpMethod = false
        allowHttpsDowngrade = false
    }

    defaultRequest {
        header("Content-Type", "application/json")
    }


    install(ContentNegotiation) {
        json(json)
    }

    HttpResponseValidator {


        handleResponseExceptionWithRequest { cause, request ->
            println(cause)
            println(request)
        }

        validateResponse { response ->
            //добавляем статусы, которые не должны вызывать ошибку
            val error =
                response.status != HttpStatusCode.OK
                        && response.status != HttpStatusCode.Created
                        && response.status != HttpStatusCode.NoContent
//                        && response.status != HttpStatusCode.BadRequest
                        && response.status != HttpStatusCode.Forbidden
                        && response.status != HttpStatusCode.Accepted
            if (response.status == HttpStatusCode.Unauthorized || response.status == HttpStatusCode.Forbidden) {
                authManager.exit()
                throw Failure.NotAuth
            }
            if (error) {
                if (response.request.url.toString().contains("Access Denied")) {
                    authManager.exit()
                    throw Failure.NotAuth
                }
                // global error handle
                val body = response.bodyAsText()
//                tryCatch {
//                    val authError =
//                        json.decodeFromString(LoginMessage.serializer(), body)
//                    if (authError.detail?.uniqueRequestId != null) {
//                        throw Failure.Login2FA(
//                            authError.detail.uniqueRequestId,
//                            authError.detail.message.orEmpty()
//                        )
//                    }
//                }

//                tryCatch {
//                    val message =
//                        json.decodeFromString(ErrorPassword.serializer(), body)
//                    message.detail ?: return@tryCatch
//                    throw Failure.ErrorPassword(
//                        type = message.detail.type.orEmpty(),
//                        message = message.detail.message.orEmpty()
//                    )
//                }
//
//                tryCatch {
//                    val message =
//                        json.decodeFromString(SmsLimit.serializer(), body)
//                    throw Failure.SmsLimit(
//                        type = message.type.orEmpty(),
//                        message = message.message.orEmpty(),
//                        ttl = message.ttl.orEmpty()
//                    )
//                }
//
//                tryCatch {
//                    val message =
//                        json.decodeFromString(ErrorMessage.serializer(), body)
//                    message.detail ?: return@tryCatch
//                    throw Failure.Http(code = response.status.value, message.detail)
//                }
//
//                tryCatch {
//                    val message =
//                        json.decodeFromString(ErrorDetailMessage.serializer(), body)
//                    message.detail?.message ?: return@tryCatch
//                    throw Failure.Http(
//                        code = response.status.value,
//                        message.detail.message
//                    )
//                }
//
//                tryCatch {
//                    val message =
//                        json.decodeFromString(Message.serializer(), body)
//                    message.message ?: return@tryCatch
//                    throw Failure.Http(
//                        code = response.status.value,
//                        message.message
//                    )
//                }
//
//                tryCatch {
//                    val message =
//                        json.decodeFromString(Message.serializer(), body)
//                    message.message ?: return@tryCatch
//                    throw Failure.Http(
//                        code = response.status.value,
//                        message.message
//                    )
//                }

                throw Failure.Message("Попробуйте позже")


            }
        }
    }

    install(HttpTimeout) {
        connectTimeoutMillis = TIME_OUT
        socketTimeoutMillis = TIME_OUT
        requestTimeoutMillis = TIME_OUT
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                println("json:\n ${message}")
            }

        }
        level = LogLevel.ALL
    }

    install(Auth) {
        bearer {
//            sendWithoutRequest {
//                true
//            }
//            loadTokens {
//                BearerTokens(authManager.token.orEmpty(), authManager.refreshToken.orEmpty())
//            }
//            refreshTokens {
//                val request = client.request("${BASE_URL}auth/refresh_tokens") {
//                    method = HttpMethod.Post
//                    setBody(RefreshTokenRequest(refreshToken = authManager.refreshToken.orEmpty()))
//                }
//                val response = request.body<RefreshTokenResponse>()
//                authManager.token = response.accessToken
//                authManager.refreshToken = response.refreshToken
//                BearerTokens(authManager.token.orEmpty(), authManager.refreshToken.orEmpty())
//            }
        }

    }


}

private fun provideKtorHttpClient(
    httpClient: HttpClient,
    baseUrl: String,
    authManager: AuthManager,
): Ktorfit {
    httpClient.sendPipeline.intercept(HttpSendPipeline.State) {
        if (!authManager.token.isNullOrBlank()) {
            //PROVIDE BEARER TOKEN
            context.headers["Authorization"] = "Bearer_${authManager.token}"
        }
        context.headers["Connection"] = "keep-alive"
        if (context.body is TextContent) {
            val json = (context.body as TextContent).text
            println(json)
        }

    }

    return ktorfit {
        baseUrl(baseUrl)
        httpClient(httpClient)
    }
}

private suspend fun tryCatch(body: suspend () -> Unit) {
    try {
        body()
    } catch (e: Exception) {
    }
}
package com.example.phoaching.screens.loginWithPassword

import com.example.phoaching.domain.usecases.auth.LoginUseCase
import com.example.phoaching.platform.BaseScreenModel
import org.koin.core.component.inject
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class LoginWithPasswordViewModel :
    BaseScreenModel<LoginWithPasswordState, LoginWithPasswordEvent>(
        LoginWithPasswordState.InitState
    ) {
    private val loginUseCase: LoginUseCase by inject()

    @OptIn(OrbitExperimental::class)
    fun changeUsername(username: String) = blockingIntent {
        reduce {
            state.copy(
                username = username
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun changePassword(password: String) = blockingIntent {
        reduce {
            state.copy(
                password = password
            )
        }
    }

    fun changePasswordVisible() = intent {
        reduce {
            state.copy(passwordVisible = !state.passwordVisible)
        }
    }

    fun login() = intent {
        launchOperation(
            operation = {
                loginUseCase.execute(LoginUseCase.Params(state.username, state.password), it)
            },
            success = {
                      postSideEffectLocal(LoginWithPasswordEvent.Success)
            },
            failure = {
                reduceLocal {
                    state.copy(error = true)
                }
            }
        )
    }
}
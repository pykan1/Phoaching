package com.example.phoaching.screens.register.confirmYourTarget

import com.example.phoaching.domain.models.ConfirmationType
import com.example.phoaching.domain.usecases.auth.GetCodeUseCase
import com.example.phoaching.domain.usecases.auth.VerifyCodeUseCase
import com.example.phoaching.platform.BaseScreenModel
import com.example.phoaching.strings.PhoachingResourceStrings
import org.koin.core.component.inject
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class ConfirmYourTargetViewModel :
    BaseScreenModel<ConfirmYourTargetState, ConfirmYourTargetEvent>(
        ConfirmYourTargetState.InitState
    ) {

    private val codeUseCase: GetCodeUseCase by inject()
    private val verifyCodeUseCase: VerifyCodeUseCase by inject()

    fun initData(email: String, phone: String) = intent {
        reduce {
            state.copy(
                email = email, phone = phone
            )
        }
    }

    @OptIn(OrbitExperimental::class)
    fun changeCode(code: String) = blockingIntent {
        reduce {
            state.copy(
                code = code
            )
        }
    }

    fun sendCode() = intent {
        launchOperation(
            operation = {
                verifyCodeUseCase.execute(
                    params = VerifyCodeUseCase.Params(
                        code = state.code,
                        id = state.codeUI.id,
                    ), it
                )
            },
            success = {
                reduceLocal {
                    state.copy(
                        code = "",
                        errorMessage = "",
                        isSuccessConfirm = true
                    )
                }
            },
            failure = {
                reduceLocal {
                    state.copy(errorCode = true)
                }
            }
        )
    }

    fun getCode() = intent {
        launchOperation(
            operation = {
                codeUseCase.execute(
                    GetCodeUseCase.Params(
                        target = if (state.isTargetEmail) state.email else state.phone,
                        confirmationType = ConfirmationType.CONFIRMATION
                    ),
                    it
                )
            },
            success = {
                reduceLocal {
                    state.copy(codeSend = true, codeUI = it, duration = 120)
                }
            }, failure = {
                reduceLocal {
                    state.copy(errorMessage = PhoachingResourceStrings.wrong_code)
                }
            }
        )
    }

    fun decrementDuration() = intent {
        reduce { state.copy(duration = state.duration - 1) }
    }

    fun next() = intent {
        when (state.isTargetEmail) {
            false -> {
                reduce {
                    state.copy(
                        duration = 0,
                        codeSend = false,
                        isTargetEmail = true,
                        isSuccessConfirm = false
                    )
                }
            }

            true -> {
                postSideEffect(ConfirmYourTargetEvent.Next)
            }
        }

    }
}
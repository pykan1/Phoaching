package com.example.phoaching.screens.smsCode

import com.example.phoaching.domain.models.auth.CodeUI
import com.example.phoaching.domain.usecases.auth.FastAuthorizationUseCase
import com.example.phoaching.platform.BaseScreenModel
import com.example.phoaching.screens.smsCode.SmsCodeState.Companion.InitState
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class SmsCodeViewModel : BaseScreenModel<SmsCodeState, SmsCodeEvent>(InitState) {

    private val sendCodeUseCase: FastAuthorizationUseCase by inject()

    fun initData(codeUI: CodeUI) = intent {
        reduce {
            state.copy(codeUI = codeUI)
        }
    }

    fun decrementSecond() = intent {
        reduce {
            state.copy(second = state.second - 1)
        }
    }

    fun changeCode(code: String) = intent {
        reduce { state.copy(code = code.take(10)) }
    }

    fun sendCode() = intent {
        launchOperation(
            operation = {
                sendCodeUseCase.execute(
                    FastAuthorizationUseCase.Params(
                        id = state.codeUI.id,
                        code = state.code
                    ), it
                )
            },
            success = {
                postSideEffectLocal(SmsCodeEvent.SuccessLogin)
            },
            failure = {
                reduceLocal {
                    state.copy(
                        error = true
                    )
                }
            }
        )
    }


}
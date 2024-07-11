package com.example.phoaching.screens.login

import com.example.phoaching.domain.models.ConfirmationType
import com.example.phoaching.domain.usecases.auth.GetCodeUseCase
import com.example.phoaching.platform.BaseScreenModel
import org.koin.core.component.inject
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class LoginViewModel : BaseScreenModel<LoginState, LoginEvent>(LoginState.InitState) {

    private val getCodeUseCase: GetCodeUseCase by inject()

    @OptIn(OrbitExperimental::class)
    fun changeText(text: String) = blockingIntent {
        reduce {
            state.copy(
                text = text.take(11).filter { it.isDigit() }
            )
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = """^[A-Za-z](.*)([@]{1})(.{1,})(\.)(.{1,})"""
        val pattern = Regex(emailRegex)
        return pattern.matches(email)
    }

    fun getCode() = intent {
        if(isValidEmail(state.text) || (state.text.all { it.isDigit() } && state.text.length == 11) && state.text.isNotBlank()) {
            launchOperation(
                operation = {
                    getCodeUseCase.execute(GetCodeUseCase.Params(target = state.text, confirmationType = ConfirmationType.FAST_AUTH), it)
                },
                success = {
                    postSideEffectLocal(LoginEvent.GetCode(it))
                },
                failure = {
                    postSideEffectLocal(LoginEvent.Register)
                }
            )
        } else {
            reduceLocal {
                state.copy(
                    error = true
                )
            }
        }
    }

    fun enterWithPassword() = intent {
        postSideEffect(LoginEvent.EnterWithPassword)
    }


}
package com.example.phoaching.screens.register.emailAndNumber

import com.example.phoaching.platform.BaseScreenModel
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

@OptIn(OrbitExperimental::class)
internal class EmailAndNumberViewModel: BaseScreenModel<EmailAndNumberState, EmailAndNumberEvent>(
    EmailAndNumberState.InitState) {

    fun changeEmail(email: String) = blockingIntent {
        reduce {
            state.copy(
                email = email
            )
        }
    }
    fun changePhone(phone: String) = blockingIntent {
        reduce {
            state.copy(
                phone = phone.take(11).filter { it.isDigit() }
            )
        }
    }

    fun next() = intent {
        if(state.email.isBlank() || state.phone.isBlank()) {
            reduce {
                state.copy(
                    error = true
                )
            }
        } else {
            postSideEffect(EmailAndNumberEvent.Next)
        }
    }

}
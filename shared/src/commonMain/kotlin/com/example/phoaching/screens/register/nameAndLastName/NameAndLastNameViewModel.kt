package com.example.phoaching.screens.register.nameAndLastName

import com.example.phoaching.platform.BaseScreenModel
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

internal class NameAndLastNameViewModel: BaseScreenModel<NameAndLastNameState, NameAndLastNameEvent>(
    NameAndLastNameState.InitState) {

    @OptIn(OrbitExperimental::class)
    fun changeName(name: String) = blockingIntent {
        reduce {
            state.copy(
                name = name
            )
        }
    }

    fun changeLastName(lastName: String) = intent {
        reduce {
            state.copy(
                lastname = lastName
            )
        }
    }

    fun next() = intent {
        if(state.lastname.isBlank() || state.name.isBlank()) {
            reduce {
                state.copy(
                    error = !state.error
                )
            }
        } else {
            postSideEffect(NameAndLastNameEvent.Next)
        }
    }


}
package com.example.phoaching.screens.getNewPassword

import com.example.phoaching.platform.BaseScreenModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class GetNewPasswordViewModel: BaseScreenModel<GetNewPasswordState, GetNewPasswordEvent>(
    GetNewPasswordState.InitState) {

    fun changeEmail(email: String) = intent {
        reduce {
            state.copy(email = email)
        }
    }

    fun restoreAccess() = intent {

    }

}
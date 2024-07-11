package com.example.phoaching.screens.register.confirmYourTarget

import com.example.phoaching.domain.models.auth.CodeUI

data class ConfirmYourTargetState(
    val codeSend: Boolean,
    val code: String,
    val codeUI: CodeUI,
    val phone: String,
    val email: String,
    val errorMessage: String,
    val isSuccessConfirm: Boolean,
    val errorCode: Boolean,
    val duration: Int,
    val isTargetEmail: Boolean, //true - email, false - phone
) {
    companion object {
        val InitState = ConfirmYourTargetState(
            false, "", CodeUI.Default, "", "", "", false,false, 0, false
        )
    }
}
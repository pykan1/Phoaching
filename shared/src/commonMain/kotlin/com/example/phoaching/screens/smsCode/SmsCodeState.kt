package com.example.phoaching.screens.smsCode

import com.example.phoaching.domain.models.auth.CodeUI

data class SmsCodeState (
    val codeUI: CodeUI,
    val second: Int,
    val code: String,
    val error: Boolean,
) {
    companion object {
        val InitState = SmsCodeState(
            second = 120,
            code = "",
            codeUI = CodeUI.Default,
            error = false
        )
    }
}
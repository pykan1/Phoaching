package com.example.phoaching.screens.getNewPassword

data class GetNewPasswordState(
    val error: Boolean,
    val email: String
) {
    companion object {
        val InitState = GetNewPasswordState(
            error = false,
            email = ""
        )
    }
}
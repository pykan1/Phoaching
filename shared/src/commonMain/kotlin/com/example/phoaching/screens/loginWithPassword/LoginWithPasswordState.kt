package com.example.phoaching.screens.loginWithPassword

data class LoginWithPasswordState(
    val error: Boolean,
    val passwordVisible: Boolean,
    val username: String,
    val password: String,
) {
    companion object {
        val InitState = LoginWithPasswordState(
            error = false,
            passwordVisible = false,

            username = "",
            password = "",
        )
    }
}
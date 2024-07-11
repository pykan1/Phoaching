package com.example.phoaching.screens.login

data class LoginState (
    val text: String, //phone or email
    val error: Boolean,
) {
    companion object {
        val InitState = LoginState(
            text = "",
            error = false
        )
    }
}
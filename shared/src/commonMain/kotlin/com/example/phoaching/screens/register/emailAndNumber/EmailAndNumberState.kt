package com.example.phoaching.screens.register.emailAndNumber

data class EmailAndNumberState (
    val email: String,
    val phone: String,
    val error: Boolean,
) {
    companion object {
        val InitState = EmailAndNumberState(
            email = "",
            phone = "",
            error = false,
        )
    }
}
package com.example.phoaching.screens.register.createPassword

data class CreatePasswordState(
    val password: String,
    val confirmPassword: String,
    val confirmPasswordVisible: Boolean,
    val passwordVisible: Boolean,
    val isValid: Boolean,
    val errorMessage: String,
) {
    companion object {
        val InitState = CreatePasswordState(
            "",
            "",
            confirmPasswordVisible = false,
            passwordVisible = false,
            isValid = false,
            errorMessage = ""
        )
    }
}
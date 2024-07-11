package com.example.phoaching.screens.register.nameAndLastName

data class NameAndLastNameState(
    val name: String,
    val lastname: String,
    val error: Boolean,
) {
    companion object {
        val InitState = NameAndLastNameState(
            "", "", false
        )
    }
}
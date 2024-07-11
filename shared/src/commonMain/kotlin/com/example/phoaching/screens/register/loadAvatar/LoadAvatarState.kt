package com.example.phoaching.screens.register.loadAvatar

data class LoadAvatarState (
    val image: String,
) {
    companion object {
        val InitState = LoadAvatarState(
            ""
        )
    }
}
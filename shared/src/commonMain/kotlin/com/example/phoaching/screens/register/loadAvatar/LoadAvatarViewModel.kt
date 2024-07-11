package com.example.phoaching.screens.register.loadAvatar

import com.example.phoaching.platform.BaseScreenModel
import com.example.phoaching.platform.permission.service.PermissionsService
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class LoadAvatarViewModel: BaseScreenModel<LoadAvatarState, LoadAvatarEvent>(LoadAvatarState.InitState) {
    val permissionsService by inject<PermissionsService>()

    fun changeImage(image: String) = intent {
        reduce {
            state.copy(
                image = image
            )
        }
    }
}
package com.example.phoaching.screens.login

import com.example.phoaching.domain.models.auth.CodeUI

sealed interface LoginEvent {
    class GetCode(val codeUI: CodeUI): LoginEvent

    data object EnterWithPassword : LoginEvent

    data object Register : LoginEvent
}
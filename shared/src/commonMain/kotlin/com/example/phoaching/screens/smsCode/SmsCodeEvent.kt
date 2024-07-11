package com.example.phoaching.screens.smsCode

sealed interface SmsCodeEvent {
    object SuccessLogin: SmsCodeEvent
}
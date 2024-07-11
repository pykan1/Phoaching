package com.example.phoaching.screens.splash

import com.example.phoaching.domain.models.DeeplinkType

sealed interface SplashScreenEvent {

    data class NavigateSplash(val deeplinkType: DeeplinkType): SplashScreenEvent

}
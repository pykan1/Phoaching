package com.example.phoaching.screens.splash

import com.example.phoaching.domain.models.DeeplinkType
import com.example.phoaching.domain.repository.PhoachingRepository
import com.example.phoaching.platform.BaseScreenModel
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

internal class SplashScreenViewModel :
    BaseScreenModel<SplashScreenState, SplashScreenEvent>(SplashScreenState()) {

    private val phoachingRepository: PhoachingRepository by inject()

    fun addPhoachingAuthor(id: String) = intent {
        println("тут2")
        launchOperation(
            operation = {
                phoachingRepository.phoachingPurchase(id)
            },
            success = {
                splashNavigate(DeeplinkType.Phoaching(id = id))
            },
            failure = {
                splashNavigate(DeeplinkType.Phoaching(id = id))
            }
        )
    }

    fun splashNavigate(deeplinkType: DeeplinkType) = intent {
        postSideEffect(SplashScreenEvent.NavigateSplash(deeplinkType = deeplinkType))
    }

}
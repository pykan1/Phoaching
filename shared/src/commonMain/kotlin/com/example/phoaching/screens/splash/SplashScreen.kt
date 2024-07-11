package com.example.phoaching.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.phoaching.domain.manager.AuthManager
import com.example.phoaching.domain.models.DeeplinkType
import com.example.phoaching.screens.login.LoginScreen
import com.example.phoaching.screens.mainTab.MainTabScreen
import com.example.phoaching.screens.mainTab.tabs.ChecklistTab
import com.example.phoaching.screens.mainTab.tabs.PhoachingTab
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashScreen(private val deeplinkType: DeeplinkType? = null) : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { SplashScreenViewModel() }
        val authManager: AuthManager by inject()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(key1 = Unit) {
            if (authManager.isAuthorized) {
                when (deeplinkType) {
                    is DeeplinkType.Phoaching -> {
                        viewModel.addPhoachingAuthor(deeplinkType.id)
                        PhoachingTab(deeplinkType)
                    }

                    null -> navigator.replaceAll(
                        MainTabScreen(
                            tab = ChecklistTab
                        )
                    )
                }
            } else {
                navigator.replaceAll(LoginScreen())
            }

            launch {
                viewModel.container.sideEffectFlow.collect {
                    when (it) {
                      is SplashScreenEvent.NavigateSplash -> {
                          navigator.replaceAll(
                              MainTabScreen(
                                  tab = PhoachingTab(it.deeplinkType)
                              )
                          )
                      }
                    }
                }
            }
        }
    }
}
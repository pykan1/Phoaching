package com.example.phoaching.screens.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.phoaching.domain.models.DeeplinkType
import com.example.phoaching.screens.mainTab.navigation.RootNavigator
import com.example.phoaching.screens.splash.SplashScreen
import com.example.phoaching.uikit.theme.PhoachingTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RootApp(deeplink: DeeplinkType? = null) {
    PhoachingTheme {
        Box(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
            BottomSheetNavigator(
                sheetShape = RoundedCornerShape(
                    topStartPercent = 8,
                    topEndPercent = 8
                )//, skipHalfExpanded = false
            ) {

                Navigator(SplashScreen(deeplink)) {
                    CompositionLocalProvider(
                        RootNavigator provides it
                    ) {
                        SlideTransition(it)
                    }


                }
            }
        }

    }
}
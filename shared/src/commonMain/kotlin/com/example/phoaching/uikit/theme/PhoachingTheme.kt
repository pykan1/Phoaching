package com.example.phoaching.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.phoaching.LocalColors
import com.example.phoaching.PhoachingColors
import com.example.phoaching.darkColors
import com.example.phoaching.lightColors


internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }


@Composable
internal fun PhoachingTheme(
    typography: PhoachingTypography = textStyles(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides if (darkTheme) darkColors() else lightColors(),
        LocalTypography provides typography,
        LocalViewConfiguration provides LocalViewConfiguration.current.updateViewConfiguration()
    ) {
        content()
    }
}


object PhoachingTheme {

    val colors: PhoachingColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: PhoachingTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

}


fun ViewConfiguration.updateViewConfiguration() = object : ViewConfiguration {
    override val longPressTimeoutMillis
        get() = this@updateViewConfiguration.longPressTimeoutMillis

    override val doubleTapTimeoutMillis
        get() = this@updateViewConfiguration.doubleTapTimeoutMillis

    override val doubleTapMinTimeMillis
        get() = this@updateViewConfiguration.doubleTapMinTimeMillis

    override val touchSlop: Float
        get() = this@updateViewConfiguration.touchSlop
    override val minimumTouchTargetSize: DpSize
        get() = DpSize(40.dp, 40.dp)
}
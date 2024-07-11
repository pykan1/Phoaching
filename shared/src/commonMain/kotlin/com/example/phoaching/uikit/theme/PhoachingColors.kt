package com.example.phoaching

import androidx.compose.runtime.compositionLocalOf
import com.example.phoaching.theme.UIKitColors
import com.example.phoaching.theme.uiKitColors

interface PhoachingColors {
    val uiKit: UIKitColors
}

class PhoachingColorsLight(
    override val uiKit: UIKitColors,


    ) : PhoachingColors


internal val LocalColors = compositionLocalOf<PhoachingColors> {
    error(
        "No colors provided! Make sure to wrap all usages of components in a " +
                "TalkTheme."
    )
}


fun lightColors(): PhoachingColors {
    return PhoachingColorsLight(
        uiKit = uiKitColors()
    )
}


fun darkColors(): PhoachingColors {
    return PhoachingColorsLight(
        uiKit = uiKitColors()
    )
}


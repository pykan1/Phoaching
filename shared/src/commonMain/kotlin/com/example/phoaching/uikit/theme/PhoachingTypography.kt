package com.example.phoaching.uikit.theme


import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.LineHeightStyle
import com.example.phoaching.typography.BaseTypography
import com.example.phoaching.platform.PhoachingAppFonts


class PhoachingTypography(
    val regular: TextStyle,
    val medium: TextStyle,
    val semiBold: TextStyle,
    val bold: TextStyle,
)


@Composable
fun textStyles(): PhoachingTypography {
    return PhoachingTypography(
        regular = PhoachingAppFonts.regular.getComposeTextStyle(),
        medium = PhoachingAppFonts.medium.getComposeTextStyle(),
        semiBold = PhoachingAppFonts.semiBold.getComposeTextStyle(),
        bold = PhoachingAppFonts.bold.getComposeTextStyle(),
    )
}

private fun toTextStyle(typographyStyle: BaseTypography): TextStyle {
    return TextStyle(
        fontSize = typographyStyle.fontSize,
        fontFamily = typographyStyle.fontFamily,
        lineHeight = typographyStyle.lineHeight,
        fontWeight = typographyStyle.fontWeight,
        platformStyle = PlatformTextStyle(
            PlatformSpanStyle.Default,
            PlatformParagraphStyle(),
        ),
        baselineShift = BaselineShift(typographyStyle.baselineShift),
        lineHeightStyle = LineHeightStyle(
            LineHeightStyle.Alignment.Center,
            LineHeightStyle.Trim.None
        )
    )
}

fun BaseTypography.getComposeTextStyle(): TextStyle {
    return toTextStyle(this)
}

internal val LocalTypography = compositionLocalOf<PhoachingTypography> {
    error(
        "No typography provided! Make sure to wrap all usages of components in a " +
                "TalkTheme."
    )
}

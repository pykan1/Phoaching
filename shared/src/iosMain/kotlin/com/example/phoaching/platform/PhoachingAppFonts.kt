package com.example.phoaching.platform

import androidx.compose.ui.text.font.FontFamily
import com.example.phoaching.typography.BaseTypography
import com.example.phoaching.typography.TextStyleBold
import com.example.phoaching.typography.TextStyleMedium
import com.example.phoaching.typography.TextStyleRegular
import com.example.phoaching.typography.TextStyleSemiBold

actual object PhoachingAppFonts {
    actual val regular: BaseTypography = TextStyleRegular(FontFamily.Default)
    actual val medium: BaseTypography = TextStyleMedium(FontFamily.Default)
    actual val semiBold: BaseTypography = TextStyleSemiBold(FontFamily.Default)
    actual val bold: BaseTypography = TextStyleBold(FontFamily.Default)

}
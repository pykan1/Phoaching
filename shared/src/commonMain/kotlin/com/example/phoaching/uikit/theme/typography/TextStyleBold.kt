package com.example.phoaching.typography

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

class TextStyleBold(override val fontFamily: FontFamily) : BaseTypography {

    override val fontSize: TextUnit = 24.sp
    override val fontWeight: FontWeight = FontWeight.W700
    override val lineHeight: TextUnit
        get() = fontSize

}
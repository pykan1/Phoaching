package com.example.phoaching.uikit

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phoaching.uikit.PhoachingButtonType.Default
import com.example.phoaching.uikit.PhoachingButtonType.Outlined
import com.example.phoaching.uikit.theme.PhoachingTheme


@Composable
fun PhoachingButton(
    modifier: Modifier = Modifier,
    type: PhoachingButtonType = Default,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    when (type) {
        Default -> {
            Button(
                enabled = enabled,
                modifier = modifier,
                onClick = {
                    onClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (enabled) PhoachingTheme.colors.uiKit.buttonBackgroundColor else PhoachingTheme.colors.uiKit.buttonDisabledColor,
                )
            ) {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = if (enabled) PhoachingTheme.colors.uiKit.lightTextColor else PhoachingTheme.colors.uiKit.textDisabledColor
                )
            }
        }

        Outlined -> {
            OutlinedButton(
                enabled = enabled,
                modifier = modifier,
                onClick = {
                    onClick()
                },
                border = BorderStroke(
                    width = 1.dp,
                    color = if (enabled) PhoachingTheme.colors.uiKit.buttonBorderColor else PhoachingTheme.colors.uiKit.buttonStrokeDisabledColor
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (enabled) PhoachingTheme.colors.uiKit.white else PhoachingTheme.colors.uiKit.buttonDisabledColor,
                )
            ) {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = if (enabled) PhoachingTheme.colors.uiKit.buttonBorderColor else PhoachingTheme.colors.uiKit.textDisabledColor,
                )
            }
        }
    }
}

enum class PhoachingButtonType {
    Default,
    Outlined
}
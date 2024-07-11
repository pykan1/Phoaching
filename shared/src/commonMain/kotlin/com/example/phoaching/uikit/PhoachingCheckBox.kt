package com.example.phoaching.uikit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phoaching.uikit.theme.PhoachingTheme

@Composable
fun PhoachingCheckBox(
    checked: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    title: String? = null,
    onCheckedChange: ((Boolean) -> Unit),
) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        val checkedColor: Color = PhoachingTheme.colors.uiKit.bottomBarColor
        val uncheckedColor: Color = Color.Unspecified
        val checkmarkColor: Color = Color.Unspecified
        val disabledCheckedColor: Color = Color.Unspecified
        val disabledUncheckedColor: Color = Color.Unspecified
        val disabledIndeterminateColor: Color = Color.Unspecified
        Checkbox(
            colors = CheckboxColors(
                checkedCheckmarkColor = PhoachingTheme.colors.uiKit.white,
                uncheckedCheckmarkColor = Color.Transparent,
                checkedBoxColor = checkedColor,
                uncheckedBoxColor = Color.Transparent,
                disabledCheckedBoxColor = disabledCheckedColor,
                disabledUncheckedBoxColor = Color.Transparent,
                disabledIndeterminateBoxColor = disabledIndeterminateColor,
                checkedBorderColor = checkedColor,
                uncheckedBorderColor =  PhoachingTheme.colors.uiKit.borderTextFieldColor,
                disabledBorderColor = disabledCheckedColor,
                disabledUncheckedBorderColor = disabledUncheckedColor,
                disabledIndeterminateBorderColor = disabledIndeterminateColor
            ),
            modifier = Modifier.size(24.dp),
            enabled = enabled,
            checked = checked,
            onCheckedChange = {
                onCheckedChange(it)
            })
        title?.let {
            Text(
                text = it,
                modifier = Modifier.padding(start = 10.dp),
                style = PhoachingTheme.typography.regular
                    .copy(
                        color = PhoachingTheme.colors.uiKit.textColor,
                        fontSize = 16.sp
                    )
            )
        }
    }

}
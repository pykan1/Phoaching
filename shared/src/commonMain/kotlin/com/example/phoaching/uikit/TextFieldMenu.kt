package com.example.phoaching.uikit

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.phoaching.ext.clickableBlank
import com.example.phoaching.uikit.theme.PhoachingTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <T> PhoachingFieldMenu(
    modifier: Modifier,
    placeholder: String? = null,
    value: String = "",
    label: @Composable (() -> Unit)? = null,
    currentValue: T,
    itemConvertText: (T) -> String, //взять текст из модельки для itemов меню
    items: List<T>,
    onClick: (T) -> Unit,
    iconEndItem: (@Composable (T) -> Unit)? = null, // Контент в конце itemа
    content: (@Composable () -> Unit)? = null, // контент в начале(первый item)
) {
    var isExpand by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        PhoachingTextField(
            readOnly = true,
            value = value,
            onChangeValue = {

            },
            placeholder = placeholder,
            label = { state, isFocused ->
                label?.invoke()
            },
            onClick = {
                isExpand = !isExpand
            },
            trailingIcon = {
                DropDownIcon(expanded = isExpand) {
                    isExpand = !isExpand
                }
            },
            modifier = Modifier
        )
        Box {
            if (isExpand) {
                Popup(
                    onDismissRequest = { isExpand = false },
//                    properties = PopupProperties(usePlatformDefaultWidth = false)
                ) {
                    Column(
                        modifier = Modifier
                            .heightIn(max = 130.dp).verticalScroll(rememberScrollState())
                            .clip(RoundedCornerShape(4.dp)).fillMaxWidth().background(
                                color = PhoachingTheme.colors.uiKit.background,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .animateContentSize().padding(horizontal = 16.dp).border(
                                width = 1.dp,
                                color = PhoachingTheme.colors.uiKit.borderTextFieldColor,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        verticalArrangement = Arrangement.spacedBy(7.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        content?.invoke()
                        items.forEachIndexed { index, item ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = itemConvertText(item),
                                    style = PhoachingTheme.typography.regular.copy(
                                        color = if (item == currentValue) PhoachingTheme.colors.uiKit.bottomBarColor else PhoachingTheme.colors.uiKit.textColor,
                                        fontSize = 13.sp
                                    ),
                                    modifier = Modifier.padding(
                                        top = if (index == 0) 7.dp else 0.dp,
                                        bottom = if (index == items.size - 1) 7.dp else 0.dp,
                                        start = 6.dp, end = 6.dp
                                    ).weight(1f).clickableBlank {
                                        onClick(item)
                                        isExpand = false
                                    }
                                )

                                Box(modifier = Modifier) {
                                    iconEndItem?.invoke(item)
                                }
                            }

                            if (index != items.size - 1) {
                                Divider(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp),
                                    color = PhoachingTheme.colors.uiKit.borderTextFieldColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
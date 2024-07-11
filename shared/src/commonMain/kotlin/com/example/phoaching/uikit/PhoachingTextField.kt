package com.example.phoaching.uikit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.skeptick.libres.compose.painterResource
import com.example.phoaching.images.PhoachingResourceImages
import com.example.phoaching.uikit.theme.PhoachingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoachingTextField(
    value: String,
    onChangeValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable ((state: PhoachingFieldState, isFocused: Boolean) -> Unit)? = null,
    bottomContent: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    centerIcon: @Composable (() -> Unit)? = null,
    placeholder: String? = null,
    passwordVisible: Boolean? = null,
    state: PhoachingFieldState = PhoachingFieldState.DEFAULT,
    visualTransformation: VisualTransformation = if (!(passwordVisible
            ?: true)
    ) PasswordVisualTransformation() else VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    minLines: Int = 1,
    maxLines: Int = 1,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    maxLength: Int = Int.MAX_VALUE,
    onClick: (() -> Unit)? = null
) {//    var _value by remember { mutableStateOf(value) }

    val focusManager = LocalFocusManager.current
//    val focusRequester = remember { FocusRequester() }
//    LaunchedEffect(value) {
//        _value = value
//    }
    var focused by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        label?.invoke(state, focused)
        TextField(
            readOnly = readOnly,
            value = value,
            onValueChange = {
                if (it.length <= maxLength) {
                    onChangeValue(it)
//                        _value = it
                }
            },
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            minLines = minLines,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            placeholder = {
                if (!placeholder.isNullOrBlank()) {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = PhoachingTheme.colors.uiKit.placeholderColor
                        )
                    )
                }
                if (centerIcon != null) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        centerIcon.invoke()
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = if (state == PhoachingFieldState.ERROR) PhoachingTheme.colors.uiKit.fieldBGErrorColor else Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = PhoachingTheme.colors.uiKit.cursorColor,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth().height(40.dp)
                .border(
                    width = 1.dp,
                    color = when {
                        state == PhoachingFieldState.ERROR -> PhoachingTheme.colors.uiKit.fieldStrokeErrorColor
                        focused -> PhoachingTheme.colors.uiKit.fieldStrokeFocusColor
                        else -> PhoachingTheme.colors.uiKit.borderTextFieldColor
                    },
                    shape = RoundedCornerShape(5.dp)
                ).clip(RoundedCornerShape(5.dp))
                .onFocusChanged {
                    focused = it.isFocused
                }
//                    .focusRequester(focusRequester)
            ,
            paddingBottom = 8.dp,
            paddingTop = 8.dp,
            enabled = state != PhoachingFieldState.DISABLED,
            singleLine = singleLine,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                if (readOnly) focusManager.clearFocus(true)
                                onClick?.invoke()
                            }
                        }
                    }
                }
        )
        AnimatedVisibility(
            visible = bottomContent != null
        ) {
            bottomContent?.invoke()
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    paddingTop: Dp,
    paddingBottom: Dp,
) {
    val textColor = textStyle.color
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    BasicTextField(value = value,
        modifier = modifier
//                    .background(colors.backgroundColor(enabled).value, shape)
//                    .indicatorLine(enabled, isError, interactionSource, colors)
        ,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(colors.cursorColor),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        minLines = minLines,
        singleLine = singleLine,
        maxLines = maxLines,
        decorationBox = @Composable { innerTextField ->
            // places leading icon, text field with label and placeholder, trailing icon
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                isError = isError,
                label = label,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                colors = colors,
                contentPadding = PaddingValues(
                    start = if (leadingIcon == null) 8.dp else 0.dp,
                    top = paddingTop,
                    bottom = paddingBottom,
                    end = if (trailingIcon == null) 8.dp else 0.dp
                ),
            )
        })
}

@Composable
internal fun PhoachingPasswordTrailIcon(visible: Boolean, onClick: (() -> Unit)) {

    val image =
        if (visible) PhoachingResourceImages.visibility_off else PhoachingResourceImages.visibility

    IconButton(onClick = {
        onClick()
    }) {
        Icon(painterResource(image), null)
    }

}


enum class PhoachingFieldState {
    DEFAULT,
    DISABLED,
    ERROR,
    SUCCESS
}
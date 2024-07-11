package com.example.phoaching.ext

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

@OptIn(ExperimentalFoundationApi::class)
internal fun Modifier.clickableBlank(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onLongClick: (() -> Unit)? = null,
    onClick: () -> Unit,
): Modifier {
    return combinedClickable(
        interactionSource = interactionSource,
        indication = null,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick,
        onLongClick = onLongClick
    )
}
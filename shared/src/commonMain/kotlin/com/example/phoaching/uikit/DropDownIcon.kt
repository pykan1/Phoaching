package com.example.phoaching.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.example.phoaching.images.PhoachingResourceImages
import io.github.skeptick.libres.compose.painterResource

@Composable
fun DropDownIcon(
    expanded: Boolean,
    onClick: () -> Unit = {}) {
    val rotate = if(!expanded) 0f else 180f
    Image(
        painter = PhoachingResourceImages.arrow_down.painterResource(),
        contentDescription = null,
        modifier = Modifier
            .rotate(rotate)
            .clickable(onClick = onClick)
    )
}
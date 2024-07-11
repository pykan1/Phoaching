package com.example.phoaching.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phoaching.ext.clickableBlank
import com.example.phoaching.images.PhoachingResourceImages
import com.example.phoaching.uikit.theme.PhoachingTheme
import io.github.skeptick.libres.compose.painterResource

@Composable
internal fun PhoachingTopBar(
    modifier: Modifier = Modifier,
    backIcon: Boolean = true,
    backIconClick: () -> Unit,
    title: String,
    subTitle: String? = null,
    infoIcon: Boolean = true,
    infoIconClick: () -> Unit,
) {

    Box(modifier = modifier) {
        if (backIcon) {
            BackButton(modifier = Modifier.align(Alignment.CenterStart)) {
                backIconClick()
            }
        }
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title, style = PhoachingTheme.typography.medium.copy(
                    color = PhoachingTheme.colors.uiKit.textColor,
                    fontSize = 18.sp
                ), modifier = Modifier
            )

            if (subTitle != null) {
                Text(
                    text = subTitle, style = PhoachingTheme.typography.regular.copy(
                        color = PhoachingTheme.colors.uiKit.textGreyColor,
                        fontSize = 16.sp
                    ), modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
        if (infoIcon) {
            Image(
                painter = PhoachingResourceImages.info_ic.painterResource(),
                contentDescription = null,
                modifier = Modifier.size(24.dp).align(Alignment.CenterEnd)
                    .clickableBlank { infoIconClick() }
            )
        }
    }
}

@Composable
internal fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        modifier = modifier,
        onClick = { onClick() }
    ) {
        Image(
            painter = PhoachingResourceImages.back_ic.painterResource(),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}
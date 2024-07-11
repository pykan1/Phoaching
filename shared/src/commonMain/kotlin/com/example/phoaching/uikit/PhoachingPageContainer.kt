package com.example.phoaching.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.phoaching.platform.Failure
import com.example.phoaching.uikit.theme.PhoachingTheme

@Composable
fun PhoachingPageContainer(
    modifier: Modifier = Modifier,
    background: Color = PhoachingTheme.colors.uiKit.background,
    isLoading: State<Boolean> = mutableStateOf(false),
    error: State<Failure?> = mutableStateOf(null),
    fill: Boolean = true,
    floatingIcon: @Composable (BoxScope.() -> Unit)? = null,
    tabNavigation: @Composable (BoxScope.(floatingIcon: @Composable (BoxScope.() -> Unit)?) -> Unit)? = null,
    header: @Composable (() -> Unit)? = null,
    footer: @Composable (BoxScope.() -> Unit)? = null,
    emptyContent: @Composable (BoxScope.() -> Unit)? = null,
    isEmpty: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    val localFocusManager = LocalFocusManager.current
    var modifier = modifier.background(background)
    modifier = if (fill) {
        modifier.fillMaxHeight()
    } else {
        modifier.padding(bottom = 8.dp)
    }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {
        header?.invoke()
        Box(
            modifier = Modifier
                .weight(1f, fill),
        ) {
            if (!isEmpty) {
                content()
            } else {
                emptyContent?.invoke(this)
            }

            tabNavigation?.invoke(this, floatingIcon) ?: Box(
                modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth().align(
                        Alignment.BottomCenter
                    )
            ) {
                Box(modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 70.dp)) {
                    floatingIcon?.invoke(this)
                }
            }
            ErrorMessageView(error = error)
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            footer?.invoke(this)
        }

    }


    if (isLoading.value) {
//        LoadingView(
//            modifier = Modifier
//                .fillMaxSize()
////                .background(Color.Black.copy(alpha = 0.5F))
//        )
    }
}

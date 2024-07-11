package com.example.phoaching.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.phoaching.uikit.theme.PhoachingTheme

@Composable
fun PhoachingAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable (() -> Unit?)? = null,
    onDismiss: () -> Unit,
    accept: () -> Unit,
    cancel: () -> Unit,
    acceptText: String,
    cancelText: String
) {

    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = { onDismiss() }) {
        Column(
            modifier = modifier.background(PhoachingTheme.colors.uiKit.white),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title, style = PhoachingTheme.typography.regular.copy(
                    color = PhoachingTheme.colors.uiKit.textColor,
                    fontSize = 16.sp,
                ),
                modifier = Modifier.padding(top = 18.dp)
            )

            content?.invoke()

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 21.dp, bottom = 22.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                PhoachingButton(
                    modifier = Modifier.height(38.dp),
                    type = PhoachingButtonType.Default,
                    text = acceptText
                ) {
                    accept()
                }

                PhoachingButton(
                    modifier = Modifier.padding(start = 20.dp).height(38.dp),
                    type = PhoachingButtonType.Outlined,
                    text = cancelText
                ) {
                    cancel()
                }
            }
        }
    }
}
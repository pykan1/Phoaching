package com.example.phoaching.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.phoaching.platform.Failure
import com.example.phoaching.screens.login.LoginScreen
import com.example.phoaching.screens.mainTab.navigation.RootNavigator
import com.example.phoaching.uikit.theme.PhoachingTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BoxScope.ErrorMessageView(error: State<Failure?>) {
    val rootNav = RootNavigator.current
    val isVisible = remember { mutableStateOf(false) }
    LaunchedEffect(error.value) {
        launch {
            if (!error.value?.message.isNullOrBlank()) {
                isVisible.value = true
                delay(1500)
                isVisible.value = false
            }
        }
    }
    if (isVisible.value) {
        Text(
            text = error.value?.message.orEmpty(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .background(PhoachingTheme.colors.uiKit.buttonBorderColor)
                .padding(8.dp)
        )
    }
    if (error.value is Failure.NotAuth) {
        rootNav?.replaceAll(LoginScreen())
    }
}
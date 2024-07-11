package com.example.phoaching.screens.register.successRegister

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.uikit.PhoachingButton
import com.example.phoaching.uikit.PhoachingPageContainer
import com.example.phoaching.uikit.theme.PhoachingTheme

class SuccessRegisterScreen(private val name: String) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        PhoachingPageContainer {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = PhoachingResourceStrings.user_success_register.format(name = name),
                    style = PhoachingTheme.typography.regular.copy(
                        color = PhoachingTheme.colors.uiKit.textColor,
                        fontSize = 18.sp,
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 200.dp)
                )

                PhoachingButton(
                    modifier = Modifier.padding(top = 10.dp).width(200.dp).height(50.dp),
                    text = PhoachingResourceStrings.enter,
                    onClick = {
                        navigator.popUntilRoot()
                    })

            }
        }
    }
}
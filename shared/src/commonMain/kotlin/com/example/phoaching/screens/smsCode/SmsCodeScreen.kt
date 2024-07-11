package com.example.phoaching.screens.smsCode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.phoaching.domain.models.auth.CodeUI
import com.example.phoaching.screens.login.LoginScreen
import com.example.phoaching.screens.loginWithPassword.LoginWithPasswordScreen
import com.example.phoaching.screens.mainTab.MainTabScreen
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.uikit.PhoachingButton
import com.example.phoaching.uikit.PhoachingButtonType
import com.example.phoaching.uikit.PhoachingFieldState
import com.example.phoaching.uikit.PhoachingPageContainer
import com.example.phoaching.uikit.PhoachingTextField
import com.example.phoaching.uikit.theme.PhoachingTheme

class SmsCodeScreen(private val codeUI: CodeUI) : Screen {

    @Composable
    override fun Content() {
        PhoachingPageContainer {
            val viewModel = rememberScreenModel { SmsCodeViewModel() }
            val state by viewModel.stateFlow.collectAsState()
            val navigator = LocalNavigator.currentOrThrow
            LaunchedEffect(viewModel) {
                viewModel.initData(codeUI)
                launch {
                    viewModel.container.sideEffectFlow.collect {event ->
                        when(event) {
                            SmsCodeEvent.SuccessLogin -> {
                                navigator.replaceAll(MainTabScreen())
                            }
                        }
                    }
                }
            }
            LaunchedEffect(state.second) {
                delay(1000L)
                if (state.second > 0) {
                    viewModel.decrementSecond()
                } else {
                    navigator.popUntil {
                        it == LoginScreen::class
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp).verticalScroll(
                    rememberScrollState()
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = PhoachingResourceStrings.enter_sms_code,
                    style = PhoachingTheme.typography.regular.copy(
                        color = PhoachingTheme.colors.uiKit.textColor,
                        fontSize = 18.sp,
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 200.dp)
                )

                PhoachingTextField(
                    placeholder = PhoachingResourceStrings.code_from_sms,
                    value = state.code,
                    state = if (state.error) PhoachingFieldState.ERROR else PhoachingFieldState.DEFAULT,
                    onChangeValue = { viewModel.changeCode(it) },
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    bottomContent = {
                        if (state.error) {
                            Text(
                                text = PhoachingResourceStrings.wrong_code,
                                style = PhoachingTheme.typography.regular.copy(
                                    color = PhoachingTheme.colors.uiKit.textColor,
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier.padding(top = 6.dp)
                            )
                        }
                    }
                )

                PhoachingButton(
                    modifier = Modifier.padding(top = 10.dp).width(250.dp).height(50.dp),
                    text = PhoachingResourceStrings.send_code_duration.format("${state.second}"),
                    onClick = {
                        viewModel.sendCode()
                    })

                PhoachingButton(
                    type = PhoachingButtonType.Outlined,
                    modifier = Modifier.padding(top = 15.dp).width(250.dp).height(50.dp),
                    text = PhoachingResourceStrings.enter_with_password,
                    onClick = {
                        navigator.replace(LoginWithPasswordScreen())
                    })
            }
        }
    }
}
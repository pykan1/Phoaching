package com.example.phoaching.screens.login

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
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.phoaching.screens.loginWithPassword.LoginWithPasswordScreen
import com.example.phoaching.screens.register.nameAndLastName.NameAndLastNameScreen
import com.example.phoaching.screens.smsCode.SmsCodeScreen
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.uikit.PhoachingButton
import com.example.phoaching.uikit.PhoachingButtonType
import com.example.phoaching.uikit.PhoachingFieldState
import com.example.phoaching.uikit.PhoachingPageContainer
import com.example.phoaching.uikit.PhoachingTextField
import com.example.phoaching.uikit.theme.PhoachingTheme
import com.example.phoaching.utils.TextFieldMask
import com.example.phoaching.utils.TextVisualTransformation

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { LoginViewModel() }
        val state by viewModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(viewModel) {
            viewModel.container.sideEffectFlow.collect { event ->
                handleSideEffect(event, navigator)
            }
        }

        PhoachingPageContainer {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp).verticalScroll(
                    rememberScrollState()
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = PhoachingResourceStrings.enter,
                    style = PhoachingTheme.typography.regular.copy(
                        color = PhoachingTheme.colors.uiKit.textColor,
                        fontSize = 18.sp,
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 200.dp)
                )

                PhoachingTextField(
                    placeholder = PhoachingResourceStrings.phone_or_email,
                    value = state.text,
                    state = if (state.error) PhoachingFieldState.ERROR else PhoachingFieldState.DEFAULT,
                    onChangeValue = { viewModel.changeText(it) },
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                    visualTransformation = TextVisualTransformation(mask = TextFieldMask.PHONE),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    bottomContent = {
                        if (state.error) {
                            Text(
                                text = PhoachingResourceStrings.wrong_form_email_phone,
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
                    modifier = Modifier.padding(top = 10.dp).width(200.dp).height(50.dp),
                    text = PhoachingResourceStrings.get_code,
                    onClick = {
                        viewModel.getCode()
                    })

                PhoachingButton(
                    type = PhoachingButtonType.Outlined,
                    modifier = Modifier.padding(top = 15.dp).width(200.dp).height(50.dp),
                    text = PhoachingResourceStrings.enter_with_password,
                    onClick = {
                        viewModel.enterWithPassword()
                    })
            }
        }
    }

    private fun handleSideEffect(
        event: LoginEvent,
        navigator: Navigator
    ) {
        when (event) {
            is LoginEvent.EnterWithPassword -> {
                navigator.push(LoginWithPasswordScreen())
            }

            is LoginEvent.GetCode -> {
                navigator.push(SmsCodeScreen(event.codeUI))
            }

            is LoginEvent.Register -> {
                navigator.push(NameAndLastNameScreen())
            }
        }
    }
}
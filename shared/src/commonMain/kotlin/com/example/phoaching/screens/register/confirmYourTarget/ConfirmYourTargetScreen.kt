package com.example.phoaching.screens.register.confirmYourTarget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.phoaching.screens.login.LoginScreen
import com.example.phoaching.screens.register.loadAvatar.LoadAvatarScreen
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.uikit.PhoachingButton
import com.example.phoaching.uikit.PhoachingFieldState
import com.example.phoaching.uikit.PhoachingPageContainer
import com.example.phoaching.uikit.PhoachingTextField
import com.example.phoaching.uikit.theme.PhoachingTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ConfirmYourTargetScreen(
    private val name: String,
    private val lastname: String,
    private val email: String,
    private val phone: String
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel {
            ConfirmYourTargetViewModel()
        }
        val navigator = LocalNavigator.currentOrThrow
        val state by viewModel.stateFlow.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.initData(email, phone)

            launch {
                viewModel.container.sideEffectFlow.collect {
                    when (it) {
                        ConfirmYourTargetEvent.Next -> {
                            navigator.push(
                                LoadAvatarScreen(
                                    name = name, lastName = lastname, email = email, phone = phone
                                )
                            )
                        }
                    }
                }
            }
        }

        LaunchedEffect(state.codeSend) {
            while (state.codeSend) {
                delay(1000L)
                if (state.duration > 0) {
                    viewModel.decrementDuration()
                } else {
                    navigator.popUntil {
                        it == LoginScreen::class
                    }
                }
            }
        }

        PhoachingPageContainer {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (
                    state.isSuccessConfirm
                ) {
                    true -> {
                        Text(
                            text = if (state.isTargetEmail) PhoachingResourceStrings.user_success_email.format(
                                email = email
                            ) else PhoachingResourceStrings.user_success_phone.format(phone = "+$phone"),
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 18.sp,
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 200.dp)
                        )

                        PhoachingButton(
                            modifier = Modifier.padding(top = 10.dp).width(200.dp).height(50.dp),
                            text = PhoachingResourceStrings.next,
                            onClick = {
                                viewModel.next()
                            })
                    }

                    false -> {
                        Text(
                            text = if (state.isTargetEmail) PhoachingResourceStrings.please_confirm_your_email.format(
                                email = email
                            ) else PhoachingResourceStrings.please_confirm_your_phone.format(phone = "+$phone"),
                            style = PhoachingTheme.typography.regular.copy(
                                color = PhoachingTheme.colors.uiKit.textColor,
                                fontSize = 18.sp,
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 200.dp)
                        )

                        if (state.codeSend) {
                            PhoachingTextField(
                                placeholder = if (state.isTargetEmail) PhoachingResourceStrings.code_from_email else PhoachingResourceStrings.code_from_sms,
                                value = state.code,
                                state = if (state.errorMessage.isNotBlank() || state.errorCode) PhoachingFieldState.ERROR else PhoachingFieldState.DEFAULT,
                                onChangeValue = { viewModel.changeCode(it) },
                                bottomContent = {
                                    if (state.errorCode) {
                                        Text(
                                            text = PhoachingResourceStrings.wrong_code,
                                            style = PhoachingTheme.typography.regular.copy(
                                                color = PhoachingTheme.colors.uiKit.textColor,
                                                fontSize = 12.sp
                                            ),
                                            modifier = Modifier.padding(top = 6.dp)
                                        )
                                    }
                                },
                                modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                            )
                        }

                        PhoachingButton(
                            modifier = Modifier.padding(top = 10.dp).width(250.dp).height(50.dp),
                            text = if (state.codeSend) PhoachingResourceStrings.send_code_duration.format(
                                duration = state.duration.toString()
                            ) else PhoachingResourceStrings.get_code,
                            onClick = {
                                when (state.codeSend) {
                                    true -> {
                                        viewModel.sendCode()
                                    }

                                    false -> {
                                        viewModel.getCode()
                                    }
                                }
                            })
                    }
                }
            }
        }
    }
}
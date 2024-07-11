package com.example.phoaching.screens.register.createPassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.phoaching.screens.register.successRegister.SuccessRegisterScreen
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.uikit.PhoachingButton
import com.example.phoaching.uikit.PhoachingFieldState
import com.example.phoaching.uikit.PhoachingPageContainer
import com.example.phoaching.uikit.PhoachingPasswordTrailIcon
import com.example.phoaching.uikit.PhoachingTextField
import com.example.phoaching.uikit.theme.PhoachingTheme
import kotlinx.coroutines.launch

class CreatePasswordScreen(
    private val name: String,
    private val lastName: String,
    private val phone: String,
    private val email: String,
    private val avatar: String,
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { CreatePasswordViewModel() }
        val state by viewModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(viewModel) {
            launch {
                viewModel.container.sideEffectFlow.collect {
                    when (it) {
                        CreatePasswordEvent.Success -> {
                            navigator.push(SuccessRegisterScreen(name = name))
                        }
                    }
                }
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
                    text = PhoachingResourceStrings.enter_your_password,
                    style = PhoachingTheme.typography.regular.copy(
                        color = PhoachingTheme.colors.uiKit.textColor,
                        fontSize = 18.sp,
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 200.dp)
                )

                PhoachingTextField(
                    placeholder = PhoachingResourceStrings.password,
                    value = state.password,
                    state = if (state.errorMessage.isNotBlank()) PhoachingFieldState.ERROR else PhoachingFieldState.DEFAULT,
                    onChangeValue = { viewModel.changePassword(it) },
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                    passwordVisible = state.passwordVisible,
                    trailingIcon = {
                        PhoachingPasswordTrailIcon(state.passwordVisible) { viewModel.changePasswordVisible() }
                    },
                )

                PhoachingTextField(
                    placeholder = PhoachingResourceStrings.repeat_password,
                    value = state.confirmPassword,
                    state = if (state.errorMessage.isNotBlank()) PhoachingFieldState.ERROR else PhoachingFieldState.DEFAULT,
                    onChangeValue = { viewModel.changeConfirmPassword(it) },
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                    passwordVisible = state.confirmPasswordVisible,
                    trailingIcon = {
                        PhoachingPasswordTrailIcon(state.confirmPasswordVisible) { viewModel.changeConfirmPasswordVisible() }
                    },
                    bottomContent = {
                        if (state.errorMessage.isNotBlank()) {
                            Text(
                                text = state.errorMessage,
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
                    text = PhoachingResourceStrings.register,
                    onClick = {
                        viewModel.register(name, lastName, phone, email, avatar)
                    })
            }
        }
    }
}
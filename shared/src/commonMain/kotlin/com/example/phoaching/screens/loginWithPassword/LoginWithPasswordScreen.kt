package com.example.phoaching.screens.loginWithPassword

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
import kotlinx.coroutines.launch
import com.example.phoaching.screens.getNewPassword.GetNewPasswordScreen
import com.example.phoaching.screens.mainTab.MainTabScreen
import com.example.phoaching.screens.register.nameAndLastName.NameAndLastNameScreen
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.uikit.PhoachingButton
import com.example.phoaching.uikit.PhoachingButtonType
import com.example.phoaching.uikit.PhoachingFieldState
import com.example.phoaching.uikit.PhoachingPageContainer
import com.example.phoaching.uikit.PhoachingPasswordTrailIcon
import com.example.phoaching.uikit.PhoachingTextField
import com.example.phoaching.uikit.theme.PhoachingTheme

class LoginWithPasswordScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { LoginWithPasswordViewModel() }
        val state by viewModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(viewModel) {
            launch {
                viewModel.container.sideEffectFlow.collect { event ->
                    when (event) {
                        LoginWithPasswordEvent.Success -> {
                            navigator.replaceAll(MainTabScreen())
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
                    text = PhoachingResourceStrings.login_with_password,
                    style = PhoachingTheme.typography.regular.copy(
                        color = PhoachingTheme.colors.uiKit.textColor,
                        fontSize = 18.sp,
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 200.dp)
                )

                PhoachingTextField(
                    placeholder = PhoachingResourceStrings.user,
                    value = state.username,
                    state = if (state.error) PhoachingFieldState.ERROR else PhoachingFieldState.DEFAULT,
                    onChangeValue = { viewModel.changeUsername(it) },
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                )

                PhoachingTextField(
                    placeholder = PhoachingResourceStrings.password,
                    value = state.password,
                    state = if (state.error) PhoachingFieldState.ERROR else PhoachingFieldState.DEFAULT,
                    onChangeValue = { viewModel.changePassword(it) },
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                    passwordVisible = state.passwordVisible,
                    trailingIcon = {
                        PhoachingPasswordTrailIcon(state.passwordVisible) { viewModel.changePasswordVisible() }
                    },
                    bottomContent = {
                        if (state.error) {
                            Text(
                                text = PhoachingResourceStrings.wrong_login_or_password,
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
                    text = PhoachingResourceStrings.enter,
                    onClick = {
                        viewModel.login()
                    })

                PhoachingButton(
                    type = PhoachingButtonType.Outlined,
                    modifier = Modifier.padding(top = 15.dp).width(200.dp).height(50.dp),
                    text = PhoachingResourceStrings.forgot_password,
                    onClick = {
                        navigator.push(GetNewPasswordScreen())
                    })

                PhoachingButton(
                    type = PhoachingButtonType.Outlined,
                    modifier = Modifier.padding(top = 15.dp).width(200.dp).height(50.dp),
                    text = PhoachingResourceStrings.register,
                    onClick = {
                        navigator.push(NameAndLastNameScreen())
                    })
            }
        }
    }
}
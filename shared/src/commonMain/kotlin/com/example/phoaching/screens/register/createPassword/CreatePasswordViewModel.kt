package com.example.phoaching.screens.register.createPassword

import com.example.phoaching.domain.usecases.auth.RegisterUseCase
import com.example.phoaching.domain.usecases.user.ChangeAvatarUseCase
import com.example.phoaching.platform.BaseScreenModel
import com.example.phoaching.strings.PhoachingResourceStrings
import com.example.phoaching.utils.PasswordValidation
import org.koin.core.component.inject
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class CreatePasswordViewModel : BaseScreenModel<CreatePasswordState, CreatePasswordEvent>(
    CreatePasswordState.InitState
) {

    private val registerUseCase: RegisterUseCase by inject()
    private val changeAvatarUseCase: ChangeAvatarUseCase by inject()

    @OptIn(OrbitExperimental::class)
    fun changePassword(it: String) = blockingIntent {
        reduce {
            state.copy(password = it.trim())
        }
        isValid()
    }

    @OptIn(OrbitExperimental::class)
    fun changeConfirmPassword(it: String) = blockingIntent {
        reduce { state.copy(confirmPassword = it.trim()) }
        isValid()
    }

    fun changePasswordVisible() = intent {
        reduce {
            state.copy(passwordVisible = !state.passwordVisible)
        }
    }

    fun changeConfirmPasswordVisible() = intent {
        reduce {
            state.copy(confirmPasswordVisible = !state.confirmPasswordVisible)
        }
    }

    private fun isValid() = intent {
        reduce {
            state.copy(isValid = PasswordValidation.isValid(state.password, state.confirmPassword))
        }
    }

    fun register(
        name: String,
        lastName: String,
        phone: String,
        email: String,
        avatar: String,
    ) = intent {
        if (!state.isValid) {
            reduce {
                state.copy(errorMessage = if (state.confirmPassword != state.password) PhoachingResourceStrings.passwords_not_match else PhoachingResourceStrings.wrong_password_format)
            }
        } else {
            launchOperation(
                operation = {
                    registerUseCase.execute(
                        RegisterUseCase.Params(
                            firstname = name,
                            lastname = lastName,
                            email = email,
                            password = state.password,
                            repeatPassword = state.confirmPassword,
                            tel = phone,
                            username = email
                        ), it
                    )
                },
                success = {
                    if(avatar.isNotBlank()) {
                        launchOperation(
                            operation = {
                                changeAvatarUseCase.execute(
                                    ChangeAvatarUseCase.Params(
                                        avatar
                                    ), it
                                )
                            }
                        )
                    }
                    postSideEffectLocal(CreatePasswordEvent.Success)
                }
            )
        }
    }
}
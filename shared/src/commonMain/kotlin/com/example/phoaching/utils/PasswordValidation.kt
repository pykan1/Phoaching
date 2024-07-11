package com.example.phoaching.utils

/**
 * Объект содержит функцию `isValid`, которая проверяет соответствие паролей строк `password` и `confirmPassword`
 * следующим критериям:
 *  Обе строки не должны быть пустыми
 *  Строки `password` и `confirmPassword` должны совпадать и быть непустыми
 *  Длина строк `password` и `confirmPassword` должна быть не менее 8 символов
 *  Строки `password` и `confirmPassword` должны содержать хотя бы одну цифру
 *  Строки `password` и `confirmPassword` должны содержать хотя бы одну заглавную букву
 *  Строки `password` и `confirmPassword` должны содержать хотя бы одну строчную букву
 *
 * @return true, если все критерии выполняются, иначе false
 */
object PasswordValidation {

    fun isValid(password: String, confirmPassword: String): Boolean {
        val symbols = "!@#$%^&*()"
        if (password.isBlank() || confirmPassword.isBlank()) return false
        val passwordIsEqual = password == confirmPassword && password.isNotBlank()
        val passwordIsNotBlank = password.isNotBlank() && confirmPassword.isNotBlank()
        val passwordLength = password.length >= 8 && confirmPassword.length >= 8
        val passHasDigit =
            password.any { it.isDigit() } && confirmPassword.any { it.isDigit() }
        val passHasUpperLetter =
            password.any { it.isUpperCase() } && confirmPassword.any { it.isUpperCase() }
        val passHasLowerLetter =
            password.any { it.isLowerCase() } && confirmPassword.any { it.isLowerCase() }
        val passHasSymbol =
            password.any { it in symbols } && confirmPassword.any { it in symbols }
        return passwordIsEqual && passwordIsNotBlank && passwordLength && passHasDigit && passHasUpperLetter && passHasLowerLetter && passHasSymbol
    }
}
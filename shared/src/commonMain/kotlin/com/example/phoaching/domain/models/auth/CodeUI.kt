package com.example.phoaching.domain.models.auth

import cafe.adriel.voyager.core.lifecycle.JavaSerializable

data class CodeUI(
    val id: String,
    val expire: Int,
    val target: String,
    val codeLength: Int,
    val targetType: String,
    val sentStamp: Int,
): JavaSerializable {
    companion object {
        val Default = CodeUI("", 0, "", 0, "", 0)
    }
}
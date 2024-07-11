package com.example.phoaching.ext

fun Number?.orEmpty(default: Number? = null) = this ?: default ?: 0
fun Float?.orEmpty(default: Float? = null) = this ?: default ?: 0F
fun Int?.orEmpty(default: Int? = null) = this ?: default ?: 0
fun Double?.orEmpty(default: Double? = null) = this ?: default ?: 0.0

fun Int?.isNotNullOrZero(): Boolean {
    return this != null && this != 0
}
package com.example.phoaching.ext

fun Any?.isNotNullInt(): Int {
    return if (this == null) 0 else 1
}
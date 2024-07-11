package com.example.phoaching.platform

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.example.phoaching.di.KoinInjector

actual fun copyUrl(url: String) {
    val context by KoinInjector.koin.inject<Context>()
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboardManager.setPrimaryClip(ClipData.newPlainText(url, url))
}
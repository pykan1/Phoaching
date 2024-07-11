package com.example.phoaching.platform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.phoaching.di.KoinInjector

actual fun shareUrl(url: String) {
    val context by KoinInjector.koin.inject<Context>()

    val sendIntent = Intent(
        Intent.ACTION_SEND
    ).apply {
        putExtra(Intent.EXTRA_TEXT, url)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(
        sendIntent, null
    ).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    ContextCompat.startActivity(context, shareIntent, Bundle())
}
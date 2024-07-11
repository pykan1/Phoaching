package com.example.phoaching.platform

import android.content.Context
import com.example.phoaching.di.KoinInjector
import com.seiko.imageloader.component.ComponentRegistryBuilder
import com.seiko.imageloader.component.setupDefaultComponents
import okio.Path
import okio.Path.Companion.toPath

actual fun ComponentRegistryBuilder.setupDefaultComponents() {
    val context by KoinInjector.koin.inject<Context>()
    this.setupDefaultComponents(context)
}

actual fun getImageCacheDirectoryPath(): Path {
    val context by KoinInjector.koin.inject<Context>()
    return context.cacheDir.absolutePath.toPath()
}

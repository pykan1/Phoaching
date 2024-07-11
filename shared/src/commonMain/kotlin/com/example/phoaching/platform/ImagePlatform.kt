package com.example.phoaching.platform

import com.seiko.imageloader.component.ComponentRegistryBuilder
import okio.Path

internal expect fun ComponentRegistryBuilder.setupDefaultComponents()
internal expect fun getImageCacheDirectoryPath(): Path
package com.example.phoaching.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.phoaching.platform.getImageCacheDirectoryPath
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberImagePainter
import com.example.phoaching.platform.setupDefaultComponents


@Composable
fun PhoachingImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    defaultAvatarBorder: Boolean = false,
) {
    CompositionLocalProvider(
        LocalImageLoader provides remember { generateImageLoader() },
    ) {
        if (url.startsWith("http")) {
            Image(
                painter = rememberImagePainter(url),
                contentDescription = contentDescription,
                modifier = modifier,
                contentScale = contentScale,
                alpha = alpha,
                alignment = alignment,
                colorFilter = colorFilter
            )
        } else {
            Image(
                painter = rememberImagePainter(url),
                contentDescription = contentDescription,
                modifier = modifier,
                contentScale = contentScale,
                alpha = alpha,
                alignment = alignment,
                colorFilter = colorFilter
            )
        }

    }

}

private fun generateImageLoader(
    memCacheSize: Int = 32 * 1024 * 1024, //32MB
    diskCacheSize: Int = 512 * 1024 * 1024 //512MB
) = ImageLoader {
    interceptor {
        memoryCacheConfig {
            maxSizeBytes(memCacheSize)
        }
        diskCacheConfig {
            directory(getImageCacheDirectoryPath())
            maxSizeBytes(diskCacheSize.toLong())
        }
    }
    components {
        setupDefaultComponents()
    }
}
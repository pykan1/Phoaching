package com.example.phoaching

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.phoaching.domain.models.DeeplinkType
import com.example.phoaching.screens.root.RootApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Handle deep link
        val action: String? = intent?.action
        val data: Uri? = intent?.data
        if (Intent.ACTION_VIEW == action && data != null) {
            // Handle the deep link URL here
            val linkId = data.lastPathSegment
            println("dataLOOOOL - $linkId")

            // Your logic to handle the link
        }
        val deeplink = data?.let {

            // Extract information from the deep link URI
            val phoachingId = data.lastPathSegment

            DeeplinkType.Phoaching(phoachingId.orEmpty())
        }
        setContent { RootApp(deeplink = deeplink) }
    }
}

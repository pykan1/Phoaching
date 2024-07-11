package com.example.phoaching.platform

import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import platform.UIKit.UIWindow
import platform.UIKit.popoverPresentationController

actual fun copyUrl(url: String) {
    val activityController = UIActivityViewController(
        activityItems = listOf(url),
        applicationActivities = null,
    )
    val window = UIApplication.sharedApplication.windows().first() as UIWindow?
    activityController.popoverPresentationController()?.sourceView =
        window
    activityController.setTitle(url)
    window?.rootViewController?.presentViewController(
        activityController as UIViewController,
        animated = true,
        completion = null,
    )
}
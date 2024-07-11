import androidx.compose.ui.window.ComposeUIViewController
import com.example.phoaching.di.KoinInjector
import com.example.phoaching.di.platformModule
import com.example.phoaching.screens.root.RootApp
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {

    KoinInjector.koin.loadModules(listOf(platformModule))

    val vc = ComposeUIViewController {
        RootApp()
    }
    return vc
}


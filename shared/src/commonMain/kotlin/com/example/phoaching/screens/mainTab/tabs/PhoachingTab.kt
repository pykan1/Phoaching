package com.example.phoaching.screens.mainTab.tabs

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.phoaching.domain.models.DeeplinkType
import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.images.PhoachingResourceImages
import com.example.phoaching.screens.foaching.PhoachingScreen
import com.example.phoaching.screens.foaching.detailPhoaching.DetailPhoachingScreen
import io.github.skeptick.libres.compose.painterResource

data class PhoachingTab(val deeplinkType: DeeplinkType?) : Tab {

    @Composable
    override fun Content() {
        val screens = when (deeplinkType) {
            is DeeplinkType.Phoaching -> listOf(
                PhoachingScreen(PhoachingTab.My),
                DetailPhoachingScreen(deeplinkType.id, null)
            )

            null -> {
                listOf(PhoachingScreen(PhoachingTab.My))
            }

            else -> {
                listOf(PhoachingScreen(PhoachingTab.My))
            }
        }
        Navigator(screens)
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 3.toUShort(),
            title = "",
            icon = PhoachingResourceImages.phoaching_tab.painterResource(),
        )
}
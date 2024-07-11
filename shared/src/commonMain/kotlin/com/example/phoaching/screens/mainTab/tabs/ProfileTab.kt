package com.example.phoaching.screens.mainTab.tabs

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.phoaching.images.PhoachingResourceImages
import io.github.skeptick.libres.compose.painterResource

object ProfileTab : Tab {

    @Composable
    override fun Content() {
//        Navigator(MainScreen())
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 5.toUShort(),
            title = "",
            icon = PhoachingResourceImages.profile_tab.painterResource(),
        )
}
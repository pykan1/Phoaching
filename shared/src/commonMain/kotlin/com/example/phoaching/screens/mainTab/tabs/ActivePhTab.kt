package com.example.phoaching.screens.mainTab.tabs

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import io.github.skeptick.libres.compose.painterResource
import com.example.phoaching.images.PhoachingResourceImages

object ActivePhTab : Tab {

    @Composable
    override fun Content() {
//        Navigator(MainScreen())
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 4.toUShort(),
            title = "",
            icon = PhoachingResourceImages.activph_tab.painterResource(),
        )
}
package com.example.phoaching.screens.mainTab

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.phoaching.screens.mainTab.tabs.ActivePhTab
import com.example.phoaching.screens.mainTab.tabs.ChecklistTab
import com.example.phoaching.screens.mainTab.tabs.MomentsTab
import com.example.phoaching.screens.mainTab.tabs.PhoachingTab
import com.example.phoaching.screens.mainTab.tabs.ProfileTab
import com.example.phoaching.uikit.theme.PhoachingTheme


class MainTabScreen(
    private val tab: Tab = ChecklistTab,
) :
    Screen {

    @Composable
    override fun Content() {
        val tabs = listOf(
            ChecklistTab,
            MomentsTab,
            PhoachingTab(null),
            ActivePhTab,
            ProfileTab
        )
        TabNavigator(
            tab = tab
        ) {
            val tabNavigator = LocalTabNavigator.current
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f)) {
                    it.current.Content()
                }
                TabRow(
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                        .background(PhoachingTheme.colors.uiKit.bottomBarColor),
                    backgroundColor = PhoachingTheme.colors.uiKit.bottomBarColor,
                    contentColor = PhoachingTheme.colors.uiKit.white,
                    selectedTabIndex = it.current.options.index.toInt() - 1
                ) {
                    tabs.forEachIndexed { index, phoachingTab ->
                        Tab(
                            modifier = Modifier.fillMaxWidth(),
                            selected = index == it.current.options.index.toInt(),
                            onClick = { tabNavigator.current = phoachingTab },
                            icon = {
                                phoachingTab.options.icon?.let {
                                    Image(
                                        painter = it,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(24.dp)
                                    )
                                }
                            },
                            selectedContentColor = PhoachingTheme.colors.uiKit.background,
                            unselectedContentColor = PhoachingTheme.colors.uiKit.background
                        )
                    }
                }
            }
        }
    }
}
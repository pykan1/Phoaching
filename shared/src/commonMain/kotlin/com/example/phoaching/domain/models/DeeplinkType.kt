package com.example.phoaching.domain.models

import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import cafe.adriel.voyager.navigator.tab.Tab
import com.example.phoaching.screens.mainTab.tabs.PhoachingTab

sealed class DeeplinkType(val tab: Tab) : JavaSerializable {
    class Phoaching(val id: String) : DeeplinkType(tab = PhoachingTab(null))
}
package com.example.phoaching.screens.foaching

import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.domain.models.phoaching.PhoachingUI

data class PhoachingState(
    val currentTab: PhoachingTab,
    val tabs: List<PhoachingTab>,
    val deleteId: String?,
    val copyId: String?,
    val myPhoachings: List<PhoachingUI>,
    val code: String,
    val isCodeAlert: Boolean,
    val authorPhoachings: List<PhoachingUI>
) {
    companion object {
        val InitState = PhoachingState(
            currentTab = PhoachingTab.My,
            tabs = PhoachingTab.entries,
            myPhoachings = emptyList(),
            authorPhoachings = emptyList(),
            deleteId = null,
            copyId = null,
            isCodeAlert = false,
            code = ""
        )
    }
}
package com.example.phoaching.ext

import com.example.phoaching.domain.models.phoaching.PhoachingTab
import com.example.phoaching.strings.PhoachingResourceStrings

fun PhoachingTab.text(): String {
    return when(this) {
        PhoachingTab.My -> PhoachingResourceStrings.my
        PhoachingTab.Author -> PhoachingResourceStrings.authors
    }
}
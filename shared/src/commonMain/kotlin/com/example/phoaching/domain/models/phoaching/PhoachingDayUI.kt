package com.example.phoaching.domain.models.phoaching

data class PhoachingDayUI(
    val number: Int,
    val title: String,
) {
    companion object {
        val Default = PhoachingDayUI(0, "")
    }
}
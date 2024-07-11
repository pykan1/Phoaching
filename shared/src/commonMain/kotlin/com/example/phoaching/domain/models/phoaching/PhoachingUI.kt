package com.example.phoaching.domain.models.phoaching

data class PhoachingUI (
    val id: String,
    val title: String,
    val author: String
) {
    companion object {
        val Default = PhoachingUI(
            id = "",
            title = "",
            author = ""
        )
    }
}
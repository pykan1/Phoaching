package com.example.phoaching.domain.models.phoaching

data class PhoachingLessonUI(
    val id: String,
    val title: String,
    val day: PhoachingDayUI,
    val number: Int,
) {
   companion object {
       val Default = PhoachingLessonUI(
            "", "", PhoachingDayUI.Default, 0
       )
   }
}
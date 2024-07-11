package com.example.phoaching.data.mapper

import com.example.phoaching.data.models.phoaching.PhoachingLessonResponse
import com.example.phoaching.data.models.phoaching.TaskDetailResponse
import com.example.phoaching.domain.models.phoaching.PhoachingDayUI
import com.example.phoaching.domain.models.phoaching.PhoachingLessonDetailUI
import com.example.phoaching.domain.models.phoaching.PhoachingLessonUI
import com.example.phoaching.domain.models.phoaching.PhoachingUI
import com.example.phoaching.ext.orEmpty

fun PhoachingLessonResponse.toUI(): PhoachingLessonUI {
    return PhoachingLessonUI(
        id = id.orEmpty(),
        title = title.orEmpty(),
        day = day?.toUI() ?: PhoachingDayUI.Default,
        number = number.orEmpty()
    )
}

fun TaskDetailResponse.toUI(): PhoachingLessonDetailUI {
    return PhoachingLessonDetailUI(
        id = id.orEmpty(),
        phoaching = phoaching?.toUI() ?: PhoachingUI.Default,
        day = day?.toUI() ?: PhoachingDayUI.Default,
        number = number.orEmpty(),
        title = title.orEmpty(),
        content = content.orEmpty(),
        scores = scores.orEmpty(),
        answer = answer.orEmpty(),
        answerType = answerType.orEmpty(),
        isInsightInDay = isInsightInDay ?: false,
        isInsightInPhoaching = isInsightInPhoching ?: false,
        textBeforeAnswer = textBeforeAnswer.orEmpty(),
        textAfterAnswer = textAfterAnswer.orEmpty(),
        pointId = pointId.orEmpty(),
        created = created.orEmpty(),
        updated = updated.orEmpty()
    )
}
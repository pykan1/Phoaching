package com.example.phoaching.data.mapper

import com.example.phoaching.data.models.getCode.GetCodeResponse
import com.example.phoaching.domain.models.auth.CodeUI
import com.example.phoaching.ext.orEmpty

fun GetCodeResponse.toUI(): CodeUI {
    return CodeUI(
        id = id.orEmpty(),
        expire = expire.orEmpty(),
        target = target.orEmpty(),
        codeLength = codeLength.orEmpty(),
        targetType = targetType.orEmpty(),
        sentStamp = sentStamp.orEmpty()
    )
}
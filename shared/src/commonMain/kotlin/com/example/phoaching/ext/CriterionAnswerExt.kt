package com.example.phoaching.ext

import com.example.phoaching.domain.models.phoaching.CriterionAnswer
import com.example.phoaching.domain.models.phoaching.CriterionAnswer.AnswerContainsKeyPhrase
import com.example.phoaching.domain.models.phoaching.CriterionAnswer.AnswerContainsKeyPhrases
import com.example.phoaching.domain.models.phoaching.CriterionAnswer.AnswerExactly
import com.example.phoaching.domain.models.phoaching.CriterionAnswer.AnswerStartByPhrase
import com.example.phoaching.strings.PhoachingResourceStrings

fun CriterionAnswer.text(): String {
    return when (this) {
        AnswerContainsKeyPhrase -> PhoachingResourceStrings.answer_contains_key_phrase
        AnswerExactly ->  PhoachingResourceStrings.answer_exactly
        AnswerStartByPhrase ->  PhoachingResourceStrings.answer_start_by_phrase
        AnswerContainsKeyPhrases ->  PhoachingResourceStrings.answer_contains_key_phrases
    }
}
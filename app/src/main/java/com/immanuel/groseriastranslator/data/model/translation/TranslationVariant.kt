package com.immanuel.groseriastranslator.data.model.translation

import com.immanuel.groseriastranslator.data.model.common.PartOfSpeech

data class TranslationVariant(
    val text: String,                // "joder", "chingar"
    val partOfSpeech: PartOfSpeech,
    val examples: List<String> = emptyList(),
    val intensity: Int = 1            // 1–5 (opcional, pero muy útil)
)

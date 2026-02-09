package com.immanuel.groseriastranslator.data.model.translation

data class Translation(
    val id: Int,
    val fromWordId: Int,     // Word raíz
    val languageTo: String,  // "es", "fr"
    val meaning: String,     // explicación educada del concepto
    val variants: List<TranslationVariant>
)
